package net.andreho.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br/>Created by a.hofmann on 22.01.2016.<br/>
 */
public final class MessageUtils {

  private static final Pattern DEFAULT_LIST_PATTERN = Pattern.compile("\\$\\{(\\d+)\\}");
  private static final Pattern DEFAULT_MAP_PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}");
  private static final String DEFAULT_UNDEFINED_REPLACEMENT = "";

  private static boolean isEscaped(String template,
                                   int index) {
    return (index - 3 >= 0) &&
           template.charAt(index - 3) == '$';
  }

  //----------------------------------------------------------------------------------------------------------------

  private static String escapedValue(String template,
                                     Matcher matcher) {
    return template.substring(matcher.start() - 2, matcher.end() + 1);
  }

  /**
   * Creates a string representation with resolved binds in form <code>${[^\}]+}</code> using provided argument list
   *
   * @param template to use parse and resolve against given arguments
   * @param map      to use for bind-resolution
   * @return a merged string representation of the given template and arguments
   */
  public static String create(String template,
                              Map<String, String> map) {
    return create(template, DEFAULT_MAP_PATTERN, DEFAULT_UNDEFINED_REPLACEMENT, map);
  }

  //----------------------------------------------------------------------------------------------------------------

  public static String create(String template,
                              Pattern pattern,
                              String undefinedBind,
                              Map<String, String> map) {
    final Matcher matcher = pattern.matcher(template);

    if (!matcher.find()) {
      return template;
    }

    final StringBuffer buffer = new StringBuffer(template.length() + 10 * map.size());

    do {
      final String key = matcher.group(1);

      String replacement;

      if (isEscaped(template, matcher.start())) {
        replacement = escapedValue(template, matcher);
      } else {
        replacement = map.getOrDefault(key, undefinedBind);
      }

      matcher.appendReplacement(buffer, replacement);
    }
    while (matcher.find());

    return matcher.appendTail(buffer)
                  .toString();
  }

  /**
   * Creates a string representation with resolved binds (<b>zero-based</b> indexing: 0,1,2 ... till args.length -
   * 1) in form <code>${\d+}</code> using provided argument list
   *
   * @param template to use parse and resolve against given arguments
   * @param args     to use for bind-resolution
   * @return a merged string representation of the given template and arguments
   */
  public static String create(String template,
                              Object... args) {
    return create(template, DEFAULT_LIST_PATTERN, DEFAULT_UNDEFINED_REPLACEMENT, args);
  }

  public static String create(String template,
                              Pattern pattern,
                              String undefinedBind,
                              Object... args) {
    final Matcher matcher = pattern.matcher(template);

    if (!matcher.find()) {
      return template;
    }

    final StringBuffer buffer = new StringBuffer(template.length() + 20);

    do {
      String replacement = undefinedBind;

      if (isEscaped(template, matcher.start())) {
        replacement = escapedValue(template, matcher);
      } else {
        final int index = Integer.parseInt(matcher.group(1)) - 1;

        if (index > -1 && index < args.length) {
          replacement = String.valueOf(args[index]);
        }
      }

      matcher.appendReplacement(buffer, replacement);
    }
    while (matcher.find());

    return matcher.appendTail(buffer)
                  .toString();
  }

  private MessageUtils() {
  }
}
