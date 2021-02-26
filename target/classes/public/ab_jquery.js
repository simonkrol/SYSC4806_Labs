var Buddy = {
    createNew: function(event) {
        var book_id = event.data.id;
        var data = {
            name: $('#name'+book_id).val(),
            phoneNumber: $('#phoneNumber'+book_id).val(),
            address: $('#address'+book_id).val()
        };
        $.ajax({
            type: "POST",
            url: "buddies/",
            data: JSON.stringify(data),
            dataType: "text",
            contentType: "application/json"
        }).then(function(data) {
            buddy = JSON.parse(data);
            $.ajax({
                type: "PATCH",
                url: "books/" + book_id + "/buddies",
                data: buddy._links.buddyInfo.href,
                dataType: "text",
                contentType: "text/uri-list"
            });
            var buddy = JSON.parse(data);
            $('#book'+book_id+'_buddies').append(Buddy.toString(buddy));
        });
    },

    toString: function(buddy) {
        return '<li>Name: ' + buddy.name + ', PhoneNumber: ' + buddy.phoneNumber + ' (Address: ' + buddy.address + ')</li>';
    }
};

var AddressBook = {
    id: function(book) {
        const str = book._links.addressBook.href;
        return str[str.length - 1];
    },

    populate: function(book) {
        var id = AddressBook.id(book);
        $.ajax({
            url: "books/" + id + "/buddies"
        }).then(function(data) {
            var buddies = data._embedded.buddies;
            buddies.forEach(function(buddy) {
                $('#book'+id+'_buddies').append(Buddy.toString(buddy));
            });

        })
    },

    createNew: function() {
        $.ajax({
            type: "POST",
            url: "books",
            data: "{}",
            dataType: "text",
            contentType: "application/json"
        }).then(function(data) {
            var book = JSON.parse(data);
            $('.books').append(AddressBook.toString(book));
            var id = AddressBook.id(book);
            $('#add-buddy'+id).bind('click', {id: id}, Buddy.createNew);
            AddressBook.populate(book);
        });
    },

    toString: function(book) {
        var id = AddressBook.id(book);
        return '<li>Address book ' + AddressBook.id(book) + ": <br/>" +
            '<label>Name</label><input type="text" id="name'+id+'">' +
            '<label>PhoneNumber</label><input type="text" id="phoneNumber'+id+'">' +
            '<label>Address</label><input type="text" id="address'+id+'">' +
            '<button id="add-buddy'+id+'">Add buddy</button>' +
            '<ul id="book' + AddressBook.id(book) + '_buddies"></ul>' +
            '</li>';
    }
};

$(document).ready(function() {
    $('#createBook').on('click', AddressBook.createNew);

    $.ajax({
        url: "books"
    }).then(function(data) {
        var books = data._embedded.books;
        books.forEach(function(book) {
            $('.books').append(AddressBook.toString(book));
            var id = AddressBook.id(book);
            $('#add-buddy'+id).bind('click', {id: id}, Buddy.createNew);
            AddressBook.populate(book);
        });
    });
});