$(function () {
    $('#btn')
        .on('click', () => {
            $('#list')
                // .append('<li>New jQuery</li>');
                .append($('<li>')
                    .text('New jQuery'));
        });

    $('#list')
        .on('click', 'li', function () {
            $(this).remove();
        });

    $('#btn-delete')
        .on('click', function () {
            $('#h1-general').remove();
        });

    $('#h1-general')
        .mouseover(function() {
            $('#h1-general')
                .css('color', 'blue')
                .text('The mouse is here!');
            $('#h1-general')
                .append($('<h2>')
                    .text('H2 tag here!')
                    .css('color', 'green')
                );
        });

    $('#h1-general')
        .mouseleave(function() {
            $(this)
                .css('color', 'red')
                .text('New Site');
        });

    $('#h1-general')
        .click(function () {
            alert('Not smart dude!');
            $(this).remove();
        });

    $('#list li')
        .hover(function() {
            $(this).toggleClass('list-hover');
        });

    $('#btn-add')
        .click(function () {
            $(document.documentElement).prepend('<h1>Newly added h1</h1>');
        });

    const incrementByOne = () => {
        const currentValue = parseInt($('#counter').text());
        return currentValue + 1;
    };

    $('#btn-counter')
        .click(function () {
            $('#counter').text(incrementByOne());
        })
});