import javax.swing.*;
import java.util.Date;

public class FridgeServer {
    /*
    Handles any fridge requests
    Handles item tracking
    Handles
     */

    //Must Have ////////////////////////////////////////

    /*
    Allow users to input what they remove and insert into the fridge by
    entering the details of the items they are adjusting on the system.
     */
    private void AddItem(String item, Date date) {
        /*
        Add Items to database
         */
    }

    public void AddItems() {

    }

    public void RemoveItem(String item) {
        /*
        Remove item
         */
    }

    private void CheckItems() {
        /*
        Track the item name, quantity and the expiry date on all
    the products that are contained within the fridge.
         */
    }

    private void OrderItems() {
        /*
        Use the generated list of items that are expiring to auto reorder the items from
    the correct supplier to maintain stock in the fridge.
         */
        //Call CheckItems
    }

    public void SeeItems() {
        /*
        Generate a list of items that can be accessed by the head chef that shows what
    items have run out or will run out soon.
         */
        //Call CheckItems
    }

    public void RemoveUser() {
        /*
    The ability to remove users from the system who no longer require access.
     */
    }

    //Should have //////////////////////////////////

    /*
    Send push app updates to the device used by the head chef to inform them
    when a product has a remaining 3 days before the expiry date.
     */
    private void NotifyHeadChef() {
        //be called by Check Items when items have only 3 days before expiring
    }

    //Be able to send reports to the health and safety officer.

    private void CheckDelivery() {
    /*
    A checking function that ensures that after a delivery the right items have
    been inserted into the system and that nothing is missing or unnecessarily added.
     */
        //compare saved OrderItems List with current delivery.
        //probably called whenever Items are added from delivery personel
    }
}
