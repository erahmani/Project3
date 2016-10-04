package businessLogic;

import dataAccess.CustomerCRUD;

public abstract class CustomerBusinessLogic {
    public static void deleteCustomer(String customerId) {
        CustomerCRUD.deleteCustomer(customerId);
    }
}
