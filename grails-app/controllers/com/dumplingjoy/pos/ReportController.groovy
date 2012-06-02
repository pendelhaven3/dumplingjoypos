package com.dumplingjoy.pos

import grails.plugins.springsecurity.Secured

class ReportController {

	def jasperService
	
	def generateSalesInvoice() {
//		def employee = Employee.get(params.id)
//		
//		def resumeData = new ResumeDTO(employee, "true".equals(params.includeName))
//		resumeData.projectHistory = ProjectMember.findAllByEmployee(employee, [sort: "startDate", order: "desc"]).collect { new ProjectHistoryDTO(it) }
		
//		forceHibernateToLoadResumeDataProperties(resumeData)
		
		def resumeData = new SalesInvoice()
		
		params._format = "PDF"
		params._file = "salesInvoice"
		params._name = "salesInvoice"
		chain(controller: 'jasper', action: 'index', model: [data: [resumeData]], params: params)
	}
	
//	private void forceHibernateToLoadResumeDataProperties(ResumeDTO resumeData) {
//		Employee employee = resumeData.employee
//		
//		employee.fullName
//		if (employee.position) {
//			employee.position.name
//		}
//		resumeData.projectHistory.each {
//			it.project.name
//			it.project.client.name
//		}
//		employee.certifications.each {
//			it.name
//		}
//		employee.seminars.each {
//			it.title
//		}
//		employee.affiliations.each {
//			it.position
//			it.organizationName
//		}
//		employee.skills.each {
//			it.skill
//			it.type.name
//		}
//		
//		resumeData.education.each { EducationDTO it ->
//			it.universityName
//		}
//		
//	}

}
