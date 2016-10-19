package hw3.utils;

/**
 * Forked from [kiuz](https://gist.github.com/kiuz/816e24aa787c2d102dd0) Modified by Xynoci on
 * 10/16/16.
 */
public class OSValidator {
    public static final String WINDOWS = "win";
    public static final String MAC = "mac";
    public static final String UNIX = "linux";
    public static final String SOLARIS = "sunos";
    public static final String UNKNOWN = "err";
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String Arch = System.getProperty("sun.arch.data.model");

    public static boolean isWindows() {
        return (OS.contains("win"));
    }

    public static boolean isMac() {
        return (OS.contains("mac"));
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }

    public static boolean isSolaris() {
        return (OS.contains("sunos"));
    }

    public static String getOS() {
        if (isWindows()) {
            return WINDOWS;
        } else if (isMac()) {
            return MAC;
        } else if (isUnix()) {
            return UNIX;
        } else if (isSolaris()) {
            return SOLARIS;
        } else {
            return UNKNOWN;
        }
    }

    public static String getOSAndArchString() {
        if (isMac()) {
            return getOS();
        }
        return getOS() + "_" + Arch;
    }
}
