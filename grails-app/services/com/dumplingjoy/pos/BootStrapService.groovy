package com.dumplingjoy.pos

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

class BootStrapService implements ApplicationContextAware {

	private ApplicationContext applicationContext
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext
	}
	
	public void importProductsFromExcel() {
		Workbook workbook = new HSSFWorkbook(applicationContext.getResource("WEB-INF/resource/product_maintenance.xls").getInputStream())
		
		Sheet sheet = workbook.getSheetAt(0)
		Iterator<Row> rows = sheet.iterator()
		rows.next() // ignore header row
		
		for (Row row : rows) {
			Cell codeCell = row.getCell(0)
			if (codeCell && !codeCell.getStringCellValue().isEmpty()) {
				Product product = new Product()
				product.code = codeCell.getStringCellValue()
				product.description = row.getCell(1).getStringCellValue()
				
				// UNITS
				
				Cell caseUnitCell = row.getCell(2)
				if (caseUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC && caseUnitCell.getNumericCellValue() > 0) {
					product.addToUnits(Unit.CSE)
				} else if (caseUnitCell.getCellType() == Cell.CELL_TYPE_STRING && !caseUnitCell.getStringCellValue().isEmpty()) {
					product.addToUnits(Unit.CSE)
				}
				
				Cell cartonUnitCell = row.getCell(3)
				if (cartonUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC && cartonUnitCell.getNumericCellValue() > 0) {
					product.addToUnits(Unit.CTN)
				} else if (cartonUnitCell.getCellType() == Cell.CELL_TYPE_STRING && !cartonUnitCell.getStringCellValue().isEmpty()) {
					product.addToUnits(Unit.CTN)
				}
				
				Cell dozenUnitCell = row.getCell(4)
				if (dozenUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC && dozenUnitCell.getNumericCellValue() > 0) {
					product.addToUnits(Unit.DOZ)
				} else if (dozenUnitCell.getCellType() == Cell.CELL_TYPE_STRING && !dozenUnitCell.getStringCellValue().isEmpty()) {
					product.addToUnits(Unit.DOZ)
				}
				
				Cell piecesUnitCell = row.getCell(5)
				if (piecesUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC && piecesUnitCell.getNumericCellValue() > 0) {
					product.addToUnits(Unit.PCS)
				} else if (piecesUnitCell.getCellType() == Cell.CELL_TYPE_STRING && !piecesUnitCell.getStringCellValue().isEmpty()) {
					product.addToUnits(Unit.PCS)
				}
				
				// UNIT CONVERSIONS
				
				if (product.units.find {it == Unit.CSE} && caseUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					if (product.units.find {it == Unit.CTN && cartonUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC}) {
						UnitConversion unitConversion = new UnitConversion()
						unitConversion.fromUnit = Unit.CSE
						unitConversion.toUnit = Unit.CTN
						unitConversion.convertedQuantity = (int)(caseUnitCell.getNumericCellValue() / cartonUnitCell.getNumericCellValue())
						product.addToUnitConversions(unitConversion)
					}
					if (product.units.find {it == Unit.DOZ}) {
						UnitConversion unitConversion = new UnitConversion()
						unitConversion.fromUnit = Unit.CSE
						unitConversion.toUnit = Unit.DOZ
						unitConversion.convertedQuantity = (int)(caseUnitCell.getNumericCellValue() / 12)
						product.addToUnitConversions(unitConversion)
					}
					if (product.units.find {it == Unit.PCS}) {
						UnitConversion unitConversion = new UnitConversion()
						unitConversion.fromUnit = Unit.CSE
						unitConversion.toUnit = Unit.PCS
						unitConversion.convertedQuantity = (int)caseUnitCell.getNumericCellValue()
						product.addToUnitConversions(unitConversion)
					}
				}
				if (product.units.find {it == Unit.CTN} && cartonUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					if (product.units.find {it == Unit.PCS}) {
						UnitConversion unitConversion = new UnitConversion()
						unitConversion.fromUnit = Unit.CTN
						unitConversion.toUnit = Unit.PCS
						unitConversion.convertedQuantity = (int)cartonUnitCell.getNumericCellValue()
						product.addToUnitConversions(unitConversion)
					}
				}
				
				product.save(failOnError:true)
			}
		}
	}

}