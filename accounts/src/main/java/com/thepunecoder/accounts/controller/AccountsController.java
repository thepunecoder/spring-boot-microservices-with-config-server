package com.thepunecoder.accounts.controller;

import com.thepunecoder.accounts.constants.AccountsConstants;
import com.thepunecoder.accounts.dto.AccountsContactInfoDto;
import com.thepunecoder.accounts.dto.CustomerDto;
import com.thepunecoder.accounts.dto.ErrorResponseDto;
import com.thepunecoder.accounts.dto.ResponseDto;
import com.thepunecoder.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * AccountsController is a REST controller that handles HTTP requests related to customer accounts.
 * It provides endpoints for creating, fetching, updating, and deleting customer account details.
**/

@Tag(
        name = "CRUD REST APIs for Accounts",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private final IAccountsService iAccountsService;

    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Test REST API",
            description = "This is to test spring boot app if it is working"
    )
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Hello from Accounts Service");
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetchAccountDetails")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
                                                               String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer &  Account details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
                                                                String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
                summary = "Get Build Version REST API",
                description = "REST API to get build version of the application")
    @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "HTTP Status OK"),
                @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))) }
        )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @Operation(
            summary = "Get JAVA Version REST API",
            description = "REST API to get java version of the application")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))) }
    )
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get Contact Info REST API",
            description = "REST API to get the contact info of the application")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class))) }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
}

/* What is rest controller in spring boot write in short ?
A REST controller in Spring Boot is a specialized type of controller that handles HTTP requests and responses in a RESTful web service.
It is annotated with @RestController, which combines @Controller and @ResponseBody, allowing the controller to return data directly as JSON or XML instead of rendering a view.
REST controllers are used to create APIs that enable communication between clients and servers using standard HTTP methods like GET, POST, PUT, and DELETE.

What is the use of ResponseEntity in spring boot?
ResponseEntity is a class in Spring Boot that represents the entire HTTP response, including the status code, headers, and body.
It is used to provide more control over the HTTP response returned from a RESTful web service.
By using ResponseEntity, developers can customize the response status, add headers, and include a response body, making it easier to handle different scenarios such as success, errors, and redirects in a more flexible manner.

//What are different types of status codes in REST API?
Different types of status codes in REST API are categorized into five classes based on the first digit of the code:
1xx (Informational): Indicates that the request has been received and is being processed (e.g., 100 Continue).
2xx (Success): Indicates that the request was successfully processed (e.g., 200 OK, 201 Created).
3xx (Redirection): Indicates that further action is needed to complete the request (e.g., 301 Moved Permanently, 302 Found).
4xx (Client Error): Indicates that there was an error with the request made by the client (e.g., 400 Bad Request, 401 Unauthorized, 404 Not Found).
5xx (Server Error): Indicates that the server encountered an error while processing the request (e.g., 500 Internal Server Error, 503 Service Unavailable).


What is the use of @RequestBody in spring boot?
@RequestBody is an annotation in Spring Boot that is used to bind the HTTP request body to a method parameter in a controller.
It allows developers to automatically deserialize the incoming JSON or XML data from the request body into a Java object.
This is particularly useful for handling POST and PUT requests where the client sends data to the server, enabling easy access and manipulation of the data within the controller method.

What is the use of @RequestParam in spring boot and difference between @PathVariable?
@RequestParam is an annotation in Spring Boot used to extract query parameters from the URL of an HTTP request and bind them to method parameters in a controller.
It is typically used for optional parameters or when the parameter names in the URL do not match the method parameter names.
On the other hand, @PathVariable is used to extract values from the URI path itself, binding them to method parameters.
It is commonly used for mandatory parameters that are part of the URL structure, such as resource identifiers.

Difference between @RestController and @Controller in spring boot?
@RestController is a specialized version of @Controller in Spring Boot that is used to create RESTful web services.
It combines the functionality of @Controller and @ResponseBody, allowing methods to return data directly as JSON or XML without the need for additional annotations.
In contrast, @Controller is used for traditional web applications where methods typically return views (like HTML pages) and require the use of @ResponseBody to return data directly.

Difference between PUT and POST method in REST API?
The main difference between PUT and POST methods in REST API lies in their purpose and idempotency.
POST is used to create a new resource on the server and is not idempotent, meaning that multiple identical POST requests can result in multiple resources being created.
PUT, on the other hand, is used to update an existing resource or create a resource if it does not exist, and it is idempotent, meaning that multiple identical PUT requests will have the same effect as a single request.

Difference between put and patch method in REST API?
The main difference between PUT and PATCH methods in REST API is in how they update resources.
PUT is used to update an entire resource, meaning that the client must send a complete representation of the resource, and any missing fields will be set to null or default values.
PATCH, on the other hand, is used to partially update a resource, allowing the client to send only the fields that need to be updated, without affecting the other fields of the resource. PATCH is more efficient for updates that only require changes to a subset of the resource's properties, while PUT is more suitable for replacing an entire resource.

 */