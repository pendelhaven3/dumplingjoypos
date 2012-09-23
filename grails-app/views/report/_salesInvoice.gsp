                             JC HARMONY SELLING INC.
                       251 GEN.P.ALVAREZ ST.CALOOCAN CITY
                             TEL.NO.3621785 3235946
	
                                  DELIVERY RECEIPT
	
Customer : <report:field value="${salesInvoice.customer.name}" length="30" />                   Page   :    ${currentPage} x ${totalPages}
Address  : <report:field value="${salesInvoice.customer.address}" length="40" />         D.R. # : <report:field value="${salesInvoice.salesInvoiceNumber}" length="8" align="right" />
Mode     : <report:field value="${salesInvoice.mode}" length="10" />                                       Date   : <report:field value="${currentDate}" />
Remarks  : <report:field value="${salesInvoice.remarks}" length="40" />         PS     : <report:field value="${salesInvoice.pricingScheme.id}" length="8" align="right" />
	
         Product Description                   Unit Qty    Price     Amount
---------------------------------------------- ---- ---- --------- ----------
<g:each var="item" in="${items}" ><report:field value="${item.product.description}" length="40" />   [ ] <report:field value="${item.unit}" length="4" /> <report:field value="${item.quantity}" length="4" align="right" /> <report:field value="${item.unitPrice}" length="9" align="right" currency="true" /> <report:field value="${item.amount}" align="right" length="10" currency="true" />
</g:each><g:if test="${isLastPage}">                                                                 ------------
Total Items => <report:field value="${salesInvoice.items.size()}" length="3" /> Total Qty => <report:field value="${salesInvoice.totalQuantity}" length="4" align="4" />                 Sub Total : <report:field value="${salesInvoice.totalAmount}" align="right" length="12" currency="true" />
                                                     Discount  :(  <report:field value="${salesInvoice.totalDiscountedAmount}" length="10" align="right" currency="true" />)
Prepared by : ____________________  Encoded by:                  ------------
                                    <report:field value="${salesInvoice.encodedBy}" length="12" />     Net Amount: <report:field value="${salesInvoice.totalNetAmount}" length="12" align="right" currency="true" />
Checked by  : ____________________                               ============
	
Received by : ____________________         Approved by : ____________________</g:if>