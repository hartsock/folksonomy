import grails.plugins.nimble.security.NimbleFilterBase;
/**
 * @author Shawn Hartsock <hartsock@acm.org>
 *
 */
class NimbleSecurityFilters extends NimbleFilterBase {

	def filters = {
		secure(action:"(create|update|edit|delete)") {
			before = {
				accessControl {
					true
				}
			}
		}
	}
	
}
