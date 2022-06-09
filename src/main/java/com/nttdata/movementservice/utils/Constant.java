package com.nttdata.movementservice.utils;

public class Constant {
    //Todo ok
    final public static String CODE_OK = "0000";
    //Se encontro un error generico
    final public static String CODE_ERROR = "0001";
    //Se encontro una excepcion
    final public static String CODE_EXCEPTION = "0002";
    //No se obtuvieron datos
    final public static String CODE_NO_DATA = "0003";
    //Error desconocido
    final public static String CODE_UNKNOWN = "0004";
    //El objeto no existe
    final public static String CODE_NO_EXIST = "0005";
    //No se pudo comunicar con el servicio de producto
    final public static String CODE_PRODUCT = "0006";
    //No se actualiza el saldo de producto
    final public static String CODE_ERROR_PRODUCT_REMAINDER = "0007";
    //No se ha superado la validacion de limites de movimientos
    final public static String CODE_ERROR_LIMIT = "0008";
    //No se ha superado la validacion de tipo de movimientos
    final public static String CODE_ERROR_TYPE_MOVEMENT = "0009";
    //Saldo insuficiente
    final public static String CODE_ERROR_INSUFFICIENT_BALANCE = "0010";
}
