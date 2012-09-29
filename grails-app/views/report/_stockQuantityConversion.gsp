                       JC HARMONY SELLING INC.
	
                  STOCK QUANTITY CONVERSION REPORT
	
SQC Ref. # : <report:field value="${stockQuantityConversion.stockQuantityConversionNumber}" length="6" align="right" />                            Date Converted: <report:field value="${currentDate}" />
Remarks    : <report:field value="${stockQuantityConversion.remarks}" length="40" />
	
                                        From  Qty To  To   To
         Product Description            Unit  Convert Unit Qty
--------------------------------------- ---- -------- ---- ----
<g:each var="item" in="${items}" ><report:field value="${item.product.description}" length="39" /> <report:field value="${item.fromUnit}" length="4" />  <report:field value="${item.quantity}" length="6" align="right" />  <report:field value="${item.toUnit}" length="4" /> <report:field value="${item.convertedQuantity}" length="4" align="right" /> 
</g:each><g:if test="${isLastPage}">
	
Prepared by   :
Verified by   :
Total Items   : <report:field value="${stockQuantityConversion.items.size()}" length="5" align="right" />              Approved by :</g:if>