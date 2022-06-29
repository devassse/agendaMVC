/**
 * Form Validation
 * @author: Joao Devson Mucavel
 */

function fieldValidation(){
	
	let name = frmContacts.name.value;
	let phone = frmContacts.phone.value;
	
	if(name === ''){
		alert('campo Nome não pode estar Vazio!')
		frmContacts.name.focus();
		break;
	} else if(phone === ''){
		alert('campo Telefone não pode estar Vazio!')
		frmContacts.phone.focus();
		break;
	}else{
		document.forms['frmContacto'].submit();
	}
	
}