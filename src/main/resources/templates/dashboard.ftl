<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div class="container-fluid disable-text-selection">
        <div class="row">
            <div class="col-12">
                <div class="mb-2">
                    <h1>Money Transactions</h1>
                </div>
                <div class="separator mb-5"></div>
            </div>
        </div>

        <div class="row icon-cards-row mb-4">
            <div class="col-md-3 col-lg-2 col-sm-4 col-6 mb-4">
                <a href="#" class="card">
                    <div class="card-body text-center">
                        <i class="iconsmind-Diploma-2"></i>
                        <p class="card-text font-weight-semibold mb-0">Finished Surveys</p>
                        <p class="lead text-center">${bills?size}</p>
                    </div>
                </a>
            </div>

            <div class="col-md-3 col-lg-2 col-sm-4 col-6 mb-4">
                <a href="#" class="card">
                    <div class="card-body text-center">
                        <i class="iconsmind-Money-Bag"></i>
                        <p class="card-text font-weight-semibold mb-0">Balance</p>
                        <p class="lead text-center">${balance} ₸</p>
                    </div>
                </a>
            </div>
        </div>

        <div class="row">
            <div class="col-12 list" data-check-all="checkAll">
                <#list bills as bill>
                    <div class="card d-flex flex-row mb-3">
                        <div class="d-flex flex-grow-1 min-width-zero">
                            <div class="card-body align-self-center d-flex flex-column flex-md-row justify-content-between min-width-zero align-items-md-center">
                                <a class="list-item-heading mb-1 truncate w-40 w-xs-100">
                                    ${bill.target}
                                </a>
                                <p class="mb-1 text-muted text-small w-15 w-xs-100">${bill.event}</p>
                                <p class="mb-1 text-muted text-small w-15 w-xs-100">${bill.createDt}</p>
                                <div class="w-15 w-xs-100">
                                    <span class="badge badge-pill badge-primary">+ ${bill.moneyAmount}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                <#else>
                    No Bills!
                </#list>
            </div>
        </div>
    </div>
</@c.page>