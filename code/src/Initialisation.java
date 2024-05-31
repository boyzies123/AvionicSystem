package code.src;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**.
 * Indicates that the annotated method is part of the initialization phase 
 * and is allowed to perform dynamic memory allocation.
 * Methods annotated with {@code @Initialisation} can be called from constructors 
 * and other methods annotated with {@code @Initialisation},
 * but should not be called from regular methods outside the initialization phase.
 * This helps ensure that all dynamic memory allocations are 
 * performed during initialization, conforming to specific memory management rules
 * that might be necessary in environments without a garbage collector.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Initialisation {
  /**.
   * An optional description of the initialization method.
   *
   * @return A string describing the initialization method.
   */
  String description() default "";
}
