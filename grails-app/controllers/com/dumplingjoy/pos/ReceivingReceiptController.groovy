package com.dumplingjoy.pos

import org.springframework.dao.DataIntegrityViolationException

class ReceivingReceiptController {

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: "receivingReceiptNumber"
		params.order = params.order ?: "desc"
        [receivingReceiptInstanceList: ReceivingReceipt.list(params), receivingReceiptInstanceTotal: ReceivingReceipt.count()]
    }

    def show() {
        def receivingReceiptInstance = ReceivingReceipt.get(params.id)
        if (!receivingReceiptInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'receivingReceipt.label', default: 'ReceivingReceipt'), params.id])
            redirect(action: "list")
            return
        }

        [receivingReceiptInstance: receivingReceiptInstance]
    }

}
