package HotelManagement.billing;
import HotelManagement.ApiResponse.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    // Method to get all billing records
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<Billing>>> getAllBillings() {
        try {
            List<Billing> billings = billingService.getAllBillings();
            return ResponseEntity.ok(new ApiResponse<>(
                    "All billings retrieved successfully",
                    HttpStatus.OK.value(),
                    billings
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(
                    "Error retrieving billings: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            ));
        }
    }


    // Method to create a new billing record
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Billing>> createBilling(@RequestBody BillingDto billingDto) {
        try {
            Billing billing = billingService.createBilling(billingDto);
            return ResponseEntity.ok(new ApiResponse<>(
                    "Billing created successfully",
                    HttpStatus.CREATED.value(),
                    billing
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(
                    "Error creating billing: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            ));
        }
    }

    // Method to retrieve a billing record by its ID
    @GetMapping("/GetById")
    public ResponseEntity<ApiResponse<Billing>> getBillingById(@PathVariable Long id) {
        try {
            Billing billing = billingService.getBillingById(id);
            return ResponseEntity.ok(new ApiResponse<>(
                    "Billing retrieved successfully",
                    HttpStatus.OK.value(),
                    billing
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                    "Billing not found: " + e.getMessage(),
                    HttpStatus.NOT_FOUND.value(),
                    null
            ));
        }
    }

    // Method to update an existing billing record
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Billing>> updateBilling(@PathVariable Long id, @RequestBody BillingDto billingDto) {
        try {
            Billing billing = billingService.updateBilling(id, billingDto);
            return ResponseEntity.ok(new ApiResponse<>(
                    "Billing updated successfully",
                    HttpStatus.OK.value(),
                    billing
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                    "Error updating billing: " + e.getMessage(),
                    HttpStatus.NOT_FOUND.value(),
                    null
            ));
        }
    }

    // Method to delete a billing record by its ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBilling(@PathVariable Long id) {
        try {
            billingService.deleteBilling(id);
            return ResponseEntity.ok(new ApiResponse<>(
                    "Billing deleted successfully",
                    HttpStatus.OK.value(),
                    null
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                    "Error deleting billing: " + e.getMessage(),
                    HttpStatus.NOT_FOUND.value(),
                    null
            ));
        }
    }
}
