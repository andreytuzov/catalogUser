/*
 * Функция для управления видимостью кнопки "Удалить все"
 */
$("input:checkbox").on("change", function() {
	var checked = $('input:checkbox:checked');
	if (checked.length > 0) {
		$('#deleteAll').css('display', 'inline');
	} else {
		$('#deleteAll').css('display', 'none');
	}	
});
 
/*
 * Функция для удаления одной записи по id
 */
function del(userID) {
	bootbox.confirm({
		message: "Вы действительно хотите выполнить удаление ?", 
		buttons: {
			confirm: {
				label: "Удалить",
				className: 'btn btn-danger'
			},
			cancel: {
				label: "Отмена",
				className: 'btn'
			}
		},
		callback: function(result) {
			if (result) {
				$.ajax({
					type: "POST",
					url: "users/delete",
					data: {id: userID},
					success: function(msg) {
						// Вывод сообщения об успешном удалении (с сервера)
						$('#info > p').html(msg);
						display_block_info(1);
						// Удаляем запись
						$('tr[data-id=' + userID + ']').remove(); 
					},
					error: function() {
						// Вывод сообщения об ошибке
						$('#info > p').html("Ошибка при удалении пользователя");
						display_block_info(1);
					}
				});
			}
		}
	});
}

/*
 * Функция для удаления нескольких записей пользователей
 */
function delAll() {
	// Собираем какие id выбраны
	var data = '';
	$('input:checkbox:checked').each(function() {
		data += $(this).closest('tr').data('id') + ",";
	});
	data = data.substring(0, data.length - 1);

	bootbox.confirm({
		message: "Вы действительно хотите выполнить удаление ?", 
		buttons: {
			confirm: {
				label: "Удалить",
				className: 'btn btn-danger'
			},
			cancel: {
				label: "Отмена",
				className: 'btn'
			}
		},
		callback: function(result) {
			if (result) {
				$.ajax({
					type: "POST",
					url: "users/deleteAll",
					data: {stringIDs: data},
					success: function(msg) {
						// Показываем сообщение об успешном удалении (с сервера)
						$('#info > p').html(msg);
						display_block_info(1);
						// Удаляем ранее выделенные checkbox
						$('input:checkbox:checked').each(function() {
							$(this).closest('tr').remove();
						});		
						// Удаление таблицы, если не осталось элементов 
						if ($('input:checkbox').length < 1) {
							$('table').remove();
						}
						$("#deleteAll").css('display', 'none');
					},
					error: function() { 
						// Показываем сообщение об ошибке
						$('#info > p').html("Ошибка при удалении");
						display_block_info(1);
					}
				});
			}
		}
	});
}

/*
 * Функция для управления видимостью информационного блока
 */
function display_block_info(active) {
	if (active == 1) {
		$('#info').css('display', 'block');
	} else {
		$('#info').css('display', 'none');
	}
}

/*
 * Функция для автоматического скрытия блока при отсутсвии информации в нем
 */
$(window).load(function() {
	var text = $('#info > p').text();
	if (text.length < 1) {
		display_block_info(0);
	}
});