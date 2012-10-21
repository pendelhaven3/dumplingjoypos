                             JC HARMONY SELLING INC.
                       251 GEN.P.ALVARE ST.CALOOCAN CITY
                             TEL.NO.3621785 3235946
	
                                 SALES REQUISITION
	
                                                            Page   :   1 of 1
Customer : ${salesRequisition.customer.name}                   S.R. # : ${salesRequisition.salesRequisitionNumber}
Address  : ${salesRequisition.customer.address1}                   Date   : ${currentDate}
           ${salesRequisition.customer.address2}                   Encoder: ${salesRequisition.encodedBy}
Terms    : ${salesRequisition.paymentTerms.name}
	
       Product Code & Description              Unit Qty    Price     Amount
-----------------------------------------------------------------------------
<g:each var="item" in="${salesRequisition.items}" >${item.product.description}    [] ${item.unit} ${item.quantity} ${item.unitPrice} ${item.amount}
</g:each>
-----------------------------------------------------------------------------
                                                     Sub Total : ${salesRequisition.totalAmount}
Total Items : ${salesRequisition.totalItems} Total Qty : ${salesRequisition.totalQuantity}                               ============
	
Remarks     : ${salesRequisition.remarks}