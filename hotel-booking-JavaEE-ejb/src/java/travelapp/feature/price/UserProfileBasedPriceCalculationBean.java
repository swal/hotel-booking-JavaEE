/**
 *     Copyright 2010 KU Leuven Research and Development - iMinds-DistriNet
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *     Administrative Contact: dnet-project-office@cs.kuleuven.be
 *     Technical Contact: stefan.walraven@cs.kuleuven.be
 */
package travelapp.feature.price;

import java.util.Date;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import travelapp.feature.price.entity.UserProfile;

/**
 * 
 * 
 * @author Stefan Walraven <stefan.walraven@cs.kuleuven.be>
 *
 * Date: 03-Dec-2010
 */
@Stateless
@Alternative
public class UserProfileBasedPriceCalculationBean implements PriceCalculation {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public double calculatePrice(double pricePerNight, Date start, Date end) {
        double normalPrice = pricePerNight * Math.ceil((end.getTime() - start.getTime())/(1000D*60*60*24));
        // TODO use an authentication service / filter / EJB
//	User user = 
        String userId = null;
	if(userId == null)
            return normalPrice;
	else {
            UserProfile up = em.find(UserProfile.class, userId);
            if(up == null)
                return normalPrice;
            else {
                int discountPercentage = up.getDiscount().getDiscountPercentage();
		return normalPrice * (1.0 - discountPercentage/100);
            }
	}
    }
}