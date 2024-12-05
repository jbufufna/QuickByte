package com.example.quickbyte.Facade;

import com.example.quickbyte.API.DTO.BusinessInfoDTO;
import com.example.quickbyte.API.DTO.CreateMenuItemDTO;
import com.example.quickbyte.API.DTO.MenuItem;
import com.example.quickbyte.API.DTO.MenuItemDTO;
import com.example.quickbyte.API.DTO.UserDTO;
import com.example.quickbyte.API.DTO.UserCreationRequestDTO;
import com.example.quickbyte.API.Services.BusinessInfoService;
import com.example.quickbyte.API.Services.ManageMenuItemService;
import com.example.quickbyte.API.Services.MenuItemService;
import com.example.quickbyte.API.Services.UserService;
import java.util.*;

public class Facade {

    private static Facade instance;


    // Business Information
    private BusinessInfoService businessInfoService;
    private String businessName;
    private String businessSlogan;
    private String businessLogo;
    private String businessPrimColor;
    private String businessSecColor;


    // Menu Item Information
    private MenuItemService menuItemService;
    private ManageMenuItemService manageMenuItemService;
    private List<MenuItem> fullMenu;


    // User Information
    private UserService userService;
    private UserDTO loggedInUser;


    private Facade() {
        businessInfoService = BusinessInfoService.getInstance();
        menuItemService = MenuItemService.getInstance();
        manageMenuItemService = ManageMenuItemService.getInstance();
        userService = UserService.getInstance();
    }

    public static synchronized Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    /*Get from database methods: */
    public String getBusinessName() {
        //businessName = "Example Business Name";
        return businessName;
    }

    public String getBusinessSlogan() {
        return businessSlogan;
    }

    public String getBusinessLogo() {
        return businessLogo;
    }

    public String getBusinessPrimColor() {
        return businessPrimColor;
    }

    public String getBusinessSecColor() {
        return businessSecColor;
    }

    public List<MenuItem> getFullMenu() {return fullMenu;}

    public UserDTO getLoggedInUser() {return loggedInUser;}


    public void getBusinessInfo(int businessId, final DatabaseCallback<BusinessInfoDTO> callback) {
        businessInfoService.getBusinessInfo(businessId, new BusinessInfoService.ApiCallback<BusinessInfoDTO>() {
            @Override
            public void onSuccess(BusinessInfoDTO result) {
                updateLocalBusinessInfoData(result);
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    public void saveBusinessInfo(BusinessInfoDTO businessInfo, final DatabaseCallback<BusinessInfoDTO> callback) {
        businessInfoService.saveOrUpdateBusinessInfo(businessInfo, new BusinessInfoService.ApiCallback<BusinessInfoDTO>() {
            @Override
            public void onSuccess(BusinessInfoDTO result) {
                updateLocalBusinessInfoData(result);
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    private void updateLocalBusinessInfoData(BusinessInfoDTO businessInfo) {
        this.businessName = businessInfo.getBusinessName();
        this.businessSlogan = businessInfo.getSlogan();
        this.businessLogo = businessInfo.getLogoUrl();
        this.businessPrimColor = businessInfo.getPrimaryColor();
        this.businessSecColor = businessInfo.getSecondaryColor();
    }

    public void getAllItems(final MenuItemService.ApiCallback<List<MenuItem>> callback) {
        menuItemService.getAllItems(new MenuItemService.ApiCallback<List<MenuItem>>() {
            @Override
            public void onSuccess(List<MenuItem> result) {
                fullMenu = result;
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });

    }


    public void createMenuItem(CreateMenuItemDTO createMenuItemDTO, final ManageMenuItemService.ApiCallback<MenuItemDTO> callback) {
        manageMenuItemService.createMenuItem(createMenuItemDTO, new ManageMenuItemService.ApiCallback<MenuItemDTO>() {
            @Override
            public void onSuccess(MenuItemDTO result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }


    public void updateMenuItem(MenuItemDTO menuItemDTO, final ManageMenuItemService.ApiCallback<MenuItemDTO> callback) {
        manageMenuItemService.updateMenuItem(menuItemDTO, new ManageMenuItemService.ApiCallback<MenuItemDTO>() {
            @Override
            public void onSuccess(MenuItemDTO result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }


    public void deleteMenuItem(int itemId, final MenuItemService.ApiCallback<Void> callback) {
        manageMenuItemService.deleteMenuItem(itemId, new ManageMenuItemService.ApiCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }


    public void loginUser(String username, String password, final UserService.ApiCallback<UserDTO> callback)
    {
        userService.loginUser(username, password, new UserService.ApiCallback<UserDTO>() {
            @Override
            public void onSuccess(UserDTO result) {
                loggedInUser = result;
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }


    public void createUser(UserCreationRequestDTO userCreationRequest, final UserService.ApiCallback<UserDTO> callback)
    {
        userService.createUser(userCreationRequest, new UserService.ApiCallback<UserDTO>() {
            @Override
            public void onSuccess(UserDTO result) {
                loggedInUser = result;
                callback.onSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }



    public interface DatabaseCallback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }


}
