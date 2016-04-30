package snowbrr

import grails.transaction.Transactional
import groovy.json.JsonBuilder

@Transactional
class ProviderService {

    def listProviders() {
        new JsonBuilder ()(
        Provider.list().inject([]) { list, provider ->
            list << new ProviderInfo(
                    id: provider.id,
                    companyName: provider.companyName,
                    email: provider.user.email,
                    firstname: provider.user.firstname,
                    lastname: provider.user.lastname,
                    address: provider.user.address,
                    city: provider.user.city,
                    province: provider.user.province,
                    country: provider.user.country,
                    latitude: provider.user.latitude,
                    longitude: provider.user.longitude,
                    phone: provider.user.phone)
            list
        })
    }
}
