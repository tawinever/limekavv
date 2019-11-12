/*
* Initialise Data Table
* */


(function () {

    /*
    * Redefining function for drawing table
    * */
    $.fn.dataTable.ext.search.push(
        function( settings, data, dataIndex ) {

            //checking slip type
            var slipType = $('#sliptype').val();

            if(slipType !== undefined && data[5] !== slipType) {
                console.log(data[5]);
                console.log(slipType);
                return false;

            }



            //checking money
            var moneyType = $('#moneyselect').val();

            console.log(parseInt(data[4].replace(',',''), 10));

            if (moneyType === "payment" && parseInt(data[4].replace(',',''), 10) < 0) {
                return false;
            }

            if (moneyType === "withdrawal" && parseInt(data[4].replace(',',''), 10) >= 0) {
                return false;
            }

            //checking datetime
            var min = parseInt( new Date($('#min').val()).getTime(), 10);
            var max = parseInt( new Date($('#max').val()).getTime(), 10);
            var age = parseInt (new Date(data[2]).getTime(), 10) || 0; // use data for the age column
            //1572544800000
            //1565502375000
            // alert(age);
            console.log(min);
            console.log(max);
            console.log(age);

            if ( ( isNaN( min ) && isNaN( max ) ) ||
                ( isNaN( min ) && age <= max ) ||
                ( min <= age   && isNaN( max ) ) ||
                ( min <= age   && age <= max ) )
            {
                return true;
            }
            return false;
        }
    );

    /*
    * Executor
    * */
    $(document).ready( function () {

        //initing datatable object
        var table = $('#table_id').DataTable( {
            orderCellsTop: true,
            dom: 'Bfrtip',
            buttons: [
                'excel'
            ],
            length: 10,
        } );

        //adding filters
        $('#table_id thead tr:eq(1) th').each( function (i) {
            if(i === 2) {
                $(this).html( '<div class="input-daterange input-group" id="datepicker">\n' +
                    '                            <input type="text" class="input-sm form-control" name="start" placeholder="Start" id="min">\n' +
                    '                            <input type="text" class="input-sm form-control" name="end" placeholder="End" id="max">\n' +
                    '                        </div>' );
            } else if(i === 4) {
                $(this).html( '<select id="moneyselect" class="form-control">\n' +
                    '                                            <option selected=""></option>\n' +
                    '                                            <option value="payment">Payment</option>\n' +
                    '                                            <option value="withdrawal">Withdrawal</option>\n' +
                    '                                        </select>' );
            } else if(i === 5) {
                $(this).html( '<select id="sliptype" class="form-control">\n' +
                    '                                            <option selected=""></option>\n' +
                    '                                            <option value="SUCCESS">Success</option>\n' +
                    '                                            <option value="IN_PROCESS">In process</option>\n' +
                    '                                        </select>' );
            } else {
                $(this).html( '<input type="text" class ="form-control" placeholder="" />' );

                $( 'input', this ).on( 'keyup change', function () {
                    if ( table.column(i).search() !== this.value ) {
                        table
                            .column(i)
                            .search( this.value )
                            .draw();
                    }
                } );
            }

        } );

        //bonding data time picker
        $('#datepicker').datepicker()
            .on("input change", function (e) {
                table.draw();
                console.log("draw");
            });

        //bonding select
        $( "#moneyselect" ).change(function() {
            table.draw();
        });

        $( "#sliptype" ).change(function() {
            table.draw();
        });



    } );


})(jQuery);