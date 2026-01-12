$(document).ready(function() {
    var searchInput = $('#searchInput');
    var autocompleteList = $('#autocomplete-list');
    var searchTimeout;

    searchInput.on('input', function() {
        clearTimeout(searchTimeout);
        var keyword = $(this).val().trim();
        
        if (keyword.length < 1) {
            autocompleteList.hide().empty();
            return;
        }
        
        searchTimeout = setTimeout(function() {
            $.ajax({
                url: 'SearchServlet?action=autocomplete',
                type: 'GET',
                data: { keyword: keyword },
                dataType: 'json',
                success: function(data) {
                    autocompleteList.empty();
                    
                    if (data && data.length > 0) {
                        $.each(data, function(index, item) {
                            var itemDiv = $('<div>')
                                .addClass('autocomplete-item')
                                .css({
                                    'padding': '8px 12px',
                                    'cursor': 'pointer',
                                    'border-bottom': '1px solid #f0f0f0'
                                })
                                .text(item.goodsName)
                                .hover(
                                    function() { $(this).css('background-color', '#f5f5f5'); },
                                    function() { $(this).css('background-color', 'white'); }
                                )
                                .click(function() {
                                    searchInput.val(item.goodsName);
                                    autocompleteList.hide();
                                    searchInput.closest('form').submit();
                                });
                            
                            autocompleteList.append(itemDiv);
                        });
                        autocompleteList.show();
                    } else {
                        autocompleteList.hide();
                    }
                },
                error: function() {
                    autocompleteList.hide();
                }
            });
        }, 300);
    });

    $(document).click(function(e) {
        if (!$(e.target).closest('.search').length) {
            autocompleteList.hide();
        }
    });

    searchInput.on('keydown', function(e) {
        if (e.keyCode === 27) {
            autocompleteList.hide();
        }
    });
});
