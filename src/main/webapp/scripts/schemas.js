$(function(){
    $.ajax({
        url: "/api/schemas",
        type: 'GET',
        cache: false,
        success: function(data) {
            console.log(data);
            fillTable(data);
        },
        error: function(error) {
            console.log('Sorry, an error occured');
        }
    });

    function fillTable(data){
        data = data.jsonSchemaMock;
        var table = $("#schemasTable tbody");
        for(var i = 0; i < data.length; i++){
            table.append('<tr><td>' + data[i].id + '</td><td><a href="' + data[i].href + '">' + data[i].href + '</a></td></tr>');
        }

    }
});
