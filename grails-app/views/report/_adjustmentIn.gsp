                             JC HARMONY SELLING INC.
                       251 GEN.P.ALVAREZ ST.CALOOCAN CITY
                             TEL.NO.3621785 3235946
	
Delivery Receipt : <report:field value="${adjustmentIn.adjustmentInNumber}" length="7" align="right" />                                   Date : <report:field value="${currentDate}" />
	
Remarks          : <report:field value="${adjustmentIn.remarks}" length="40" />  Page :    ${currentPage} x ${totalPages}
	
<report:field value="" />
                                                        
         Product Description                  Unit  Qty 
--------------------------------------------- ---- -----
<g:each var="item" in="${items}" ><report:field value="${item.product.description}" length="45" /> <report:field value="${item.unit}" length="4" /> <report:field value="${item.quantity}" length="5" align="right" />
</g:each><g:if test="${isLastPage}">
	
<report:field value="" />
	
<report:field value="" />
	
Prepared by   :
Verified by   :
Total Items   : <report:field value="${adjustmentIn.items.size()}" length="5" align="right" />                Approved by :</g:if>