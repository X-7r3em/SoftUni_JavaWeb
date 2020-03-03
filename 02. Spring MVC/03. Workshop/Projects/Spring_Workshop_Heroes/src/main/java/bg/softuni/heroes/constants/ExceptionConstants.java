package bg.softuni.heroes.constants;

import bg.softuni.heroes.errors.NoSuchHeroException;

import java.util.function.Supplier;

public class ExceptionConstants {
    public static final Supplier<RuntimeException> NO_SUCH_HERO_EXCEPTION = () -> new NoSuchHeroException("No such hero!");
}
