package com.dumplingjoy.pos

import java.math.RoundingMode

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

class BootStrapService implements ApplicationContextAware {

	private static final int PRODUCT_CODE_COLUMN = 1
	private static final int PRODUCT_DESCRIPTION_COLUMN = 0
	private static final int CASE_UNIT_COLUMN = 2
	private static final int CARTON_UNIT_COLUMN = 3
	private static final int DOZEN_UNIT_COLUMN = 4
	private static final int PIECES_UNIT_COLUMN = 5
	private static final int CASE_PRICE_COLUMN = 6
	private static final int CARTON_PRICE_COLUMN = 7
	private static final int DOZEN_PRICE_COLUMN = 8
	private static final int PIECES_PRICE_COLUMN = 9
	
	private ApplicationContext applicationContext
	
	def productUnitPriceService
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext
	}
	
	public void importProductsFromExcel() {
		Workbook workbook = new HSSFWorkbook(applicationContext.getResource("WEB-INF/resource/product_maintenance.xls").getInputStream())
		
		Sheet sheet = workbook.getSheetAt(0)
		Iterator<Row> rows = sheet.iterator()
		// ignore first three header rows
		rows.next()
		rows.next()
		
		for (Row row : rows) {
			Cell codeCell = row.getCell(PRODUCT_CODE_COLUMN)
			if (codeCell && !codeCell.getStringCellValue().isEmpty()) {
				Product product = new Product()
				product.code = codeCell.getStringCellValue().trim()
				product.description = row.getCell(PRODUCT_DESCRIPTION_COLUMN).getStringCellValue().trim()
				
				// UNITS
				
				Cell caseUnitCell = row.getCell(CASE_UNIT_COLUMN)
				if (caseUnitCell) {
					if (caseUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC && caseUnitCell.getNumericCellValue() > 0) {
						product.addToUnits(Unit.CSE)
					} else if (caseUnitCell.getCellType() == Cell.CELL_TYPE_STRING && !caseUnitCell.getStringCellValue().trim().isEmpty()) {
						product.addToUnits(Unit.CSE)
					}
				}
				
				Cell cartonUnitCell = row.getCell(CARTON_UNIT_COLUMN)
				if (cartonUnitCell) {
					if (cartonUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC && cartonUnitCell.getNumericCellValue() > 0) {
						product.addToUnits(Unit.CTN)
					} else if (cartonUnitCell.getCellType() == Cell.CELL_TYPE_STRING && !cartonUnitCell.getStringCellValue().trim().isEmpty()) {
						product.addToUnits(Unit.CTN)
					}
				}
				
				Cell dozenUnitCell = row.getCell(DOZEN_UNIT_COLUMN)
				if (dozenUnitCell) {
					if (dozenUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC && dozenUnitCell.getNumericCellValue() > 0) {
						product.addToUnits(Unit.DOZ)
					} else if (dozenUnitCell.getCellType() == Cell.CELL_TYPE_STRING && !dozenUnitCell.getStringCellValue().trim().isEmpty()) {
						product.addToUnits(Unit.DOZ)
					}
				}
				
				Cell piecesUnitCell = row.getCell(PIECES_UNIT_COLUMN)
				if (piecesUnitCell) {
					if (piecesUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC && piecesUnitCell.getNumericCellValue() > 0) {
						product.addToUnits(Unit.PCS)
					} else if (piecesUnitCell.getCellType() == Cell.CELL_TYPE_STRING && !piecesUnitCell.getStringCellValue().trim().isEmpty()) {
						product.addToUnits(Unit.PCS)
					}
				}
				
				// UNIT CONVERSIONS
				
				if (product.units.find {it == Unit.CSE} && caseUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					if (product.units.find {it == Unit.CTN && cartonUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC}) {
						UnitConversion unitConversion = new UnitConversion()
						unitConversion.fromUnit = Unit.CSE
						unitConversion.toUnit = Unit.CTN
						unitConversion.convertedQuantity = getIntegerCellValue(cartonUnitCell)
						product.addToUnitConversions(unitConversion)
					}
					if (product.units.find {it == Unit.DOZ}) {
						UnitConversion unitConversion = new UnitConversion()
						unitConversion.fromUnit = Unit.CSE
						unitConversion.toUnit = Unit.DOZ
						unitConversion.convertedQuantity = getIntegerCellValue(dozenUnitCell)
						product.addToUnitConversions(unitConversion)
					}
					if (product.units.find {it == Unit.PCS}) {
						UnitConversion unitConversion = new UnitConversion()
						unitConversion.fromUnit = Unit.CSE
						unitConversion.toUnit = Unit.PCS
						unitConversion.convertedQuantity = getIntegerCellValue(piecesUnitCell)
						product.addToUnitConversions(unitConversion)
					}
				}
				if (product.units.find {it == Unit.CTN} && cartonUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					if (product.units.find {it == Unit.DOZ}) {
						UnitConversion unitConversion = new UnitConversion()
						unitConversion.fromUnit = Unit.CTN
						unitConversion.toUnit = Unit.DOZ
						unitConversion.convertedQuantity = getIntegerCellValue(dozenUnitCell)
						product.addToUnitConversions(unitConversion)
					}
					if (product.units.find {it == Unit.PCS}) {
						UnitConversion unitConversion = new UnitConversion()
						unitConversion.fromUnit = Unit.CTN
						unitConversion.toUnit = Unit.PCS
						unitConversion.convertedQuantity = getIntegerCellValue(piecesUnitCell)
						product.addToUnitConversions(unitConversion)
					}
				}
				if (product.units.find {it == Unit.DOZ} && dozenUnitCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					if (product.units.find {it == Unit.PCS}) {
						UnitConversion unitConversion = new UnitConversion()
						unitConversion.fromUnit = Unit.DOZ
						unitConversion.toUnit = Unit.PCS
						unitConversion.convertedQuantity = getIntegerCellValue(piecesUnitCell)
						product.addToUnitConversions(unitConversion)
					}
				}

				product.save(failOnError:true)
			}
		}
	}
	
	private int getIntegerCellValue(Cell cell) {
		int value = 0;
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			value = (int)cell.getNumericCellValue()
		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			value = Integer.valueOf(cell.getStringCellValue())		
		}
		return value
	}

	public void importUnitPricesFromExcel() {
		PricingScheme pricingScheme = PricingScheme.getCanvasserPricingScheme()
		
		Workbook workbook = new HSSFWorkbook(applicationContext.getResource("WEB-INF/resource/product_maintenance.xls").getInputStream())
		
		Sheet sheet = workbook.getSheetAt(0)
		Iterator<Row> rows = sheet.iterator()
		// ignore first three header rows
		rows.next()
		rows.next()
		
		for (Row row : rows) {
			Cell codeCell = row.getCell(PRODUCT_CODE_COLUMN)
			if (codeCell && !codeCell.getStringCellValue().isEmpty()) {
				Product product = Product.findByCode(codeCell.getStringCellValue().trim())
				
				ProductUnitPrice unitPrice = null
				
				println "product: " + product.description
				
				if (product.units.find {it == Unit.CSE} && row.getCell(CASE_PRICE_COLUMN)) {
					unitPrice = pricingScheme.unitPrices.find {it.product == product && it.unit == Unit.CSE}
					unitPrice.price = new BigDecimal(row.getCell(CASE_PRICE_COLUMN).getNumericCellValue())
				} else if (product.units.find {it == Unit.CTN && row.getCell(CARTON_PRICE_COLUMN)}) {
					unitPrice = pricingScheme.unitPrices.find {it.product == product && it.unit == Unit.CTN}
					unitPrice.price = new BigDecimal(row.getCell(CARTON_PRICE_COLUMN).getNumericCellValue())
				} else if (product.units.find {it == Unit.DOZ} && row.getCell(DOZEN_PRICE_COLUMN)) {
					unitPrice = pricingScheme.unitPrices.find {it.product == product && it.unit == Unit.DOZ}
					unitPrice.price = new BigDecimal(row.getCell(DOZEN_PRICE_COLUMN).getNumericCellValue())
				} else if (product.units.find {it == Unit.PCS} && row.getCell(PIECES_PRICE_COLUMN)) {
					unitPrice = pricingScheme.unitPrices.find {it.product == product && it.unit == Unit.PCS}
					unitPrice.price = new BigDecimal(row.getCell(PIECES_PRICE_COLUMN).getNumericCellValue())
				}
				
				if (unitPrice) {
					productUnitPriceService.update(unitPrice)
				}
			}
			
		}
	}
		
}