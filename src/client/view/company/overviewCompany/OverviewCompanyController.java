package client.view.company.overviewCompany;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import client.view.company.editCompanyProfile.EditCompanyProfileViewModel;

public class OverviewCompanyController implements FXMLController {

    private ViewHandler viewHandler;
    private OverviewCompanyViewModel overviewVM;

    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        overviewVM = (OverviewCompanyViewModel) vm;

    }
}
