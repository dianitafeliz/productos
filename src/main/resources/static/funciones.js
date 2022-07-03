function eliminarUsuario(IdUsuario){
	swal({
  title: "Esta Seguro de eliminar ?",
  text: "Esta apunto de eliminar un usuario!",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((Ok) => {
  if (Ok) {
	$.ajax({
		url:"/usuarios/eliminar/"+IdUsuario,
		    success: function(res) {
			console.log(res);
		}
	});
    swal("El usuario se elimino", {
      icon: "success",
    }).then((ok)=>{
	if(ok){
		location.href="/usuarios";
	}
});
  } else {
    swal("Usuario no eliminado!");
  }
});
}

function eliminarProvedor(IdProvedores){
	swal({
  title: "Esta Seguro de eliminar ?",
  text: "Esta apunto de eliminar un provedor!",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((Ok) => {
  if (Ok) {
	$.ajax({
		url:"/listarProvedores/eliminar/"+IdProvedores,
		    success: function(res) {
			console.log(res);
		}
	});
    swal("El Provedor se elimino", {
      icon: "success",
    }).then((ok)=>{
	if(ok){
		location.href="/listaProvedores";
	}
});
  } else {
    swal("Provedor no eliminado!");
  }
});
}
function eliminarProductos(IdProducto){
	swal({
  title: "Esta Seguro de eliminar ?",
  text: "Esta apunto de eliminar un un producto!",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((Ok) => {
  if (Ok) {
	$.ajax({
		url:"/productos/eliminar/"+IdProducto,
		    success: function(res) {
			console.log(res);
		}
	});
    swal("El producto se elimino", {
      icon: "success",
    }).then((ok)=>{
	if(ok){
		location.href="/productos";
	}
});
  } else {
    swal("Productos no eliminado!");
  }
});
}
function eliminarVentas(IdVenta){
	swal({
  title: "Esta Seguro de eliminar ?",
  text: "Esta apunto de eliminar una venta!",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((Ok) => {
  if (Ok) {
	$.ajax({
		url:"/ventas/eliminar/"+IdVenta,
		    success: function(res) {
			console.log(res);
		}
	});
    swal("La venta se elimino", {
      icon: "success",
    }).then((ok)=>{
	if(ok){
		location.href="/Ventas";
	}
});
  } else {
    swal("Venta no eliminado!");
  }
});
}
function eliminarInsumos(IdInsumo){
	swal({
  title: "Esta Seguro de eliminar ?",
  text: "Esta apunto de eliminar un un Insumo!",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((Ok) => {
  if (Ok) {
	$.ajax({
		url:"/Insumos/eliminar/"+IdInsumo,
		    success: function(res) {
			console.log(res);
		}
	});
    swal("El Insumo se elimino", {
      icon: "success",
    }).then((ok)=>{
	if(ok){
		location.href="/Insumos";
	}
});
  } else {
    swal("Insumo no eliminado!");
  }
});
}

//_______________________________________________________________



//________________________________________________________________

function reporte(){
	
	var fecha1 = document.getElementById("fecha1").value;
	var fecha2 = document.getElementById("fecha2").value;
	var tip0 = document.getElementById("tip0").value;
	var dir = 
	
	window.open("http://localhost:8085/report/ventas/download?fecha_inicio="+fecha1+"&fecha_fin="+fecha2+"&tipo="+tip0);
	
	
}

function reporte2(){
	
	var fecha3 = document.getElementById("fecha3").value;
	var fecha4 = document.getElementById("fecha4").value;
	var tipo1 = document.getElementById("tipo1").value;
	var dir = 
	
	window.open("http://localhost:8085/Ventas/ventas/download?fechaInicio="+fecha3+"&fechaFin="+fecha4+"&tipo="+tipo1);
	
	
}

function reporte3(){
	
	var fecha5 = document.getElementById("fecha5").value;
	var fecha6 = document.getElementById("fecha6").value;
	var tipo2 = document.getElementById("tipo2").value;
	var dir = 
	
	window.open("http://localhost:8085/reporteTotales/ventas/download?fechaInicio="+fecha5+"&fechaFin="+fecha6+"&tipo="+tipo2);
	
	
}

function reporte4(){
	
	var fecha7 = document.getElementById("fecha7").value;
	var fecha8 = document.getElementById("fecha8").value;
	var tipo3 = document.getElementById("tipo3").value;
	var dir = 
	
	window.open("http://localhost:8085/Vendedor/ventas/download?fechaInicio="+fecha7+"&fechaFin="+fecha8+"&tipo="+tipo3);
	
	
}
function checkPasswordMatch(fieldConfirmPassword) {
    if (fieldConfirmPassword.value != $("#password").val()) {
        fieldConfirmPassword.setCustomValidity("las contraseñas no conciden!");
    } else {
        fieldConfirmPassword.setCustomValidity("");
    }
}

