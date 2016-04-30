package snowbrr

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	String email

	String firstname

	String lastname

	String address

	String city

	String province

	String country

	String phone

	double latitude

	double longitude

	byte[] avatar

	User(String username, String password) {
		this()
		this.username = username
		this.password = password
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		email email: true, nullable: false
		firstname nullable: false
		lastname nullable: false
		address nullable: false
		city nullable: false
		province nullable: false
		country ( nullable: false, inList: ['Canada', 'United States'] )
		avatar(nullable:true, maxSize: 5242880 )
		latitude nullable: false
		longitude nullable: false
		phone nullable: true, phone: true
	}

	static hasMany = [messages: Message, transactions: Transaction]

	static mapping = {
		password column: '`password`'
	}
}
