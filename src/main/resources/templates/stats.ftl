<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<#setting date_format="dd-MM-yyyy">
<#setting locale="en_US">
<@c.admin>
    <div class="container-fluid">
        <div class="row">
            <div class="col-12">

                <h1>Dashboard Analytics</h1>
                <div class="separator mb-5"></div>
            </div>
        </div>

        <div class="row icon-cards-row mb-4">
            <div class="col-md-3 col-lg-2 col-sm-4 col-6 mb-4">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="iconsmind-Sum-2"></i>
                        <p class="card-text font-weight-semibold mb-0">Total Sum</p>
                        <p class="lead text-center">${totalEarnedSum} KZT</p>
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-lg-2 col-sm-4 col-6 mb-4">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="iconsmind-Money-Smiley"></i>
                        <p class="card-text font-weight-semibold mb-0">Paid</p>
                        <p class="lead text-center">${totalWithdrawnSum} KZT</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
<#--                        <div class="input-daterange input-group" id="datepicker" style="margin-bottom: 10px;">-->
<#--                            <input type="text" class="input-sm form-control" name="start" placeholder="Start" id="min">-->
<#--                            <span class="input-group-addon"></span>-->
<#--                            <input type="text" class="input-sm form-control" name="end" placeholder="End" id="max">-->
<#--                        </div>-->
                        <table id="table_id" class="display">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Date</th>

                        <th>Event</th>
                        <th>Money(KZT)</th>
                        <th>Status</th>
                    </tr>
                    <tr>
                        <th></th>
                        <th></th>
                        <th></th>

                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list transfers as t>
                    <tr>
                        <td>${t.getName()}</td>
                        <td>${t.getEmail()} </td>
                        <td>${t.getDate()?number_to_date?string("MM/dd/yyyy")} </td>
                        <td>${t.getEvent()} </td>
                        <td>${t.getSum()} </td>
                        <td>${t.getStatus()} </td>
                    </tr>
                    <#else>
                        No entries.
                    </#list>
                    </tbody>

                </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
</@c.admin>