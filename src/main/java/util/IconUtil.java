package util;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * 用来获取对应的图标
 */
public class IconUtil extends Properties {

        /**
         * 因为LinkedHashSet有序，所以，key在调用put()的时候，存放到这里也就有序。
         */
        private final LinkedHashSet<Object> keys = new LinkedHashSet<>();

        @Override
        public Enumeration<Object> keys() {
            return Collections.enumeration(keys);
        }

        /**
         * 在put的时候，只是把key有序的存到{@link IconUtil#keys}
         * 取值的时候，根据有序的keys，可以有序的取出所有value
         * 依然调用父类的put方法,也就是key value 键值对还是存在hashTable里.
         * 只是现在多了个存key的属性{@link IconUtil#keys}
         */
        @Override
        public Object put(Object key, Object value) {
            keys.add(key);
            return super.put(key, value);
        }

        /**
         * 因为复写了这个方法，在（方式一）的时候,才输出有序。
         */
        @Override
        public Set<String> stringPropertyNames() {
            Set<String> set = new LinkedHashSet<>();
            for (Object key : this.keys) {
                set.add((String) key);
            }
            return set;
        }

        /**
         * 因为复写了这个方法，在（方式二）的时候,才输出有序。
         */
        @Override
        public Set<Object> keySet() {
            return keys;
        }

        /**
         * 因为复写了这个方法，在（方式四）的时候,才输出有序。
         */
        @Override
        public Enumeration<?> propertyNames() {
            return Collections.enumeration(keys);
        }

        public ImageIcon getIcon(String iconName){
            ImageIcon icon = new ImageIcon(iconName);
            System.out.println(iconName);
            icon.setImage(icon.getImage().getScaledInstance(40, 40,
                    Image.SCALE_AREA_AVERAGING));
            return icon;
        }
}
