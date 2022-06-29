/**
 * Form Validation
 * @author: Joao Devson Mucavel
 * @param id
 */

function confirmDelete(id){
	let result = confirm("Tem certeza que deseja apagar?");
	if(result === true){
		window.location.href = "delete?id=" + id;
	}
}