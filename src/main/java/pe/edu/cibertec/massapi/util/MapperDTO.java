package pe.edu.cibertec.massapi.util;

import org.springframework.stereotype.Component;
import pe.edu.cibertec.massapi.persistence.model.*;
import pe.edu.cibertec.massapi.presentation.dto.*;

@Component
public class  MapperDTO {

    public UsuarioDTO usuarioToDTOBasico(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNumeroTelefono(usuario.getNumeroTelefono());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setRol(usuario.getRol().name());
        usuarioDTO.setNombre(usuario.getNombre());

        return usuarioDTO;
    }


    public DireccionDTO direccionToDTOBasico(Direccion direccion) {
        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setId(direccion.getId());
        direccionDTO.setCiudad(direccion.getCiudad());
        direccionDTO.setCalle(direccion.getCalle());
        direccionDTO.setEstado(direccion.getEstado());
        direccionDTO.setPais(direccion.getPais());
        direccionDTO.setCodigoPostal(direccion.getCodigoPostal());

        return direccionDTO;
    }


    public CategoriaDTO categoriaToDTOBasico(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNombre(categoria.getNombre());

        return categoriaDTO;
    }


    public ItemPedidoDTO itemPedidoToDTOBasico(ItemPedido itemPedido) {
        ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
        itemPedidoDTO.setId(itemPedido.getId());
        itemPedidoDTO.setCantidad(itemPedido.getCantidad());
        itemPedidoDTO.setPrecio(itemPedido.getPrecio());
        itemPedidoDTO.setEstado(itemPedido.getEstado().name());
        itemPedidoDTO.setCreadoEn(itemPedido.getCreadoEn());

        return itemPedidoDTO;
    }


    public ProductoDTO productoToDTOBasico(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setUrlImagen(producto.getUrlImagen());

        return productoDTO;
    }


    public UsuarioDTO usuarioToDTOPlusDireccion(Usuario usuario){
        UsuarioDTO usuarioDTO = usuarioToDTOBasico(usuario);

        if(usuario.getDireccion()!=null){

            System.out.println("DIRECCION NO ES NULO");
            DireccionDTO direccionDTO = direccionToDTOBasico(usuario.getDireccion());
            usuarioDTO.setDireccion(direccionDTO);
        }

        return usuarioDTO;
    }


    public ItemPedidoDTO itemPedidoToDTOPlusProducto(ItemPedido itemPedido){
        ItemPedidoDTO itemPedidoDTO = itemPedidoToDTOBasico(itemPedido);

        if(itemPedido.getProducto()!=null){
            ProductoDTO productoDTO = productoToDTOBasico(itemPedido.getProducto());
            itemPedidoDTO.setProducto(productoDTO);
        }

        return itemPedidoDTO;
    }


    public ItemPedidoDTO itemPedidoToDTOPlusProductoAndUsuario(ItemPedido itemPedido){
        ItemPedidoDTO itemPedidoDTO = itemPedidoToDTOPlusProducto(itemPedido);

        if(itemPedido.getUsuario()!=null){
            UsuarioDTO usuarioDTO = usuarioToDTOPlusDireccion(itemPedido.getUsuario());
            itemPedidoDTO.setUsuario(usuarioDTO);
        }

        return itemPedidoDTO;
    }


    public UsuarioDTO usuarioToDTOPlusDireccionAndPedidoHistorial(Usuario usuario){
        UsuarioDTO usuarioDTO = usuarioToDTOPlusDireccion(usuario);

        if(usuario.getListaItemsPedido() != null && !usuario.getListaItemsPedido().isEmpty()){
            usuarioDTO.setListaItemsPedido(usuario.getListaItemsPedido()
                    .stream()
                    .map(this::itemPedidoToDTOPlusProducto)
                    .toList());
        }

        return usuarioDTO;
    }


}
