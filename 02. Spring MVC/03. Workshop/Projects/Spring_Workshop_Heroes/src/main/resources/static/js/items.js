const URLS = {
    items: '/api/items',
};

const loader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    }
};


const toString = ({ id, name, slot, stamina, strength, attack, defence, free }) => {
    let columns = `
        <td>${name}</td>
        <td>${slot}</td>
        <td>${stamina}</td>
        <td>${strength}</td>
        <td>${attack}</td>
        <td>${defence}</td>
        `;

    columns += free ?
        `
        <td>
        <form class="buy-item-form" action="/api/items/${id}" method="post"> 
        <button class ="btn btn-info">Buy</button>
        </form>
        </td> 
    `
        : '<td></td>'
        ;
    return `<tr>${columns}</tr>`;
};

loader.show();
fetch(URLS.items)
    .then(response => response.json())
    .then(items => {
        let result = '';
        items.forEach(item => {
            let itemInfo = toString(item);
            result += itemInfo;
        });
        $('#items-table').html(result);
        loader.hide();
    });

$('#items-table').on('submit', '.buy-item-form', function (ev) {
    let url = $(this).attr('action');

    loader.show();
    fetch(url, { method: 'post' })
        .then(resp => {
            console.log(resp);
            loader.hide();
            window.location.href="/items/merchant-async";
        });
    ev.preventDefault();
    return false;
});