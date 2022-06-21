package com.nttdata.movementservice.utils;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.RequestTypeMovement;
import com.nttdata.movementservice.model.api.ResponseMovement;
import com.nttdata.movementservice.model.entity.Movement;
import com.nttdata.movementservice.model.entity.TypeMovement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.springframework.beans.BeanUtils;

/**
 * Esta clase contiene funcionalidades utiles
 */
public class Utils {
  /**
   * Este método se encarga de convertir un objeto de base de datos a un request
   *
   * @param movement objeto de base de datos
   * @return RequestMovement objeto devuelto al api
   */
  public static RequestMovement movementToRequest(Movement movement) {
    RequestMovement request = new RequestMovement();
    BeanUtils.copyProperties(movement, request);

    return request;
  }

  /**
   * Este método se encarga de convertir un objeto request a un objeto de base de datos
   *
   * @param request objeto request
   * @return Movement objeto de base de datos
   */
  public static Movement RequestToMovement(RequestMovement request) {
    Movement movement = new Movement();
    BeanUtils.copyProperties(request, movement);

    return movement;
  }

  /**
   * Este método se encarga de convertir un objeto de base de datos a un response
   *
   * @param movement objeto de base de datos
   * @return ResponseMovement objeto devuelto al api
   */
  public static ResponseMovement movementToResponse(Movement movement) {
    ResponseMovement response = new ResponseMovement();
    BeanUtils.copyProperties(movement, response);

    return response;
  }

  /**
   * Este método se encarga de convertir un objeto request a un objeto de base de datos
   *
   * @param request objeto request
   * @return TypeMovement objeto de base de datos
   */
  public static TypeMovement RequestToTypeMovement(RequestTypeMovement request) {
    TypeMovement typeMovement = new TypeMovement();
    BeanUtils.copyProperties(request, typeMovement);

    return typeMovement;
  }

  /**
   * Este método se encarga de validar si una fecha pertenece al mes actual
   *
   * @param date fecha a validar
   * @return Boolean resultado de la validacion
   */
  public static Boolean isCurrentMonth(Date date) {
    Boolean result = false;

    LocalDate currentDate = LocalDate.now();

    LocalDate newDate = Instant.ofEpochMilli(date.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

    if (currentDate.getMonth() == newDate.getMonth() && currentDate.getYear() == newDate.getYear()) {
      result = true;
    }

    return result;
  }

  /**
   * Este método se encarga de validar si una fecha pertenece al dia actual
   *
   * @param date fecha a validar
   * @return Boolean resultado de la validacion
   */
  public static Boolean isCurrentDay(Date date) {
    Boolean result = false;

    LocalDate currentDate = LocalDate.now();

    LocalDate newDate = Instant.ofEpochMilli(date.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

    if (currentDate.getDayOfMonth() == newDate.getDayOfMonth() && currentDate.getMonth() == newDate.getMonth() && currentDate.getYear() == newDate.getYear()) {
      result = true;
    }

    return result;
  }

  /**
   * Este método se encarga de validar si una fecha pertenece un dia especifico del mes
   *
   * @param numDay dia del mes a validar
   * @return Boolean resultado de la validacion
   */
  public static Boolean isDayMonth(Date date, int numDay) {
    Boolean result = false;

    LocalDate newDate = Instant.ofEpochMilli(date.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

    if (newDate.getDayOfMonth() == numDay) {
      result = true;
    }

    return result;
  }

  /**
   * Este método obtiene una fecha correspondiente al primer dia del mes actual
   *
   * @return Date fecha obtenida
   */
  public static Date getFirstDayCurrentMonth() {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(new Date());
    calendar.set(Calendar.DAY_OF_MONTH, 1);

    return calendar.getTime();
  }

  /**
   * Este método obtiene una fecha correspondiente al ultimo dia del mes actual
   *
   * @return Date fecha obtenida
   */
  public static Date getLastDayCurrentMonth() {
    return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
  }
}
