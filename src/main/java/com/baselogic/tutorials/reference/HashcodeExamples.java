package com.baselogic.tutorials.reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * From HashMap:
 * The HashMap class in its internal implementation puts objects (keys) in different buckets based on their hashcode.
 *
 * When you query an element or you check if a key is contained in the map, first the proper bucket is looked for
 * based on the hashcode of the queried key. Inside the bucket objects are checked in a sequencial way,
 * and inside a bucket only the equals() method is used to compare elements.
 *
 * So if you do not override Object.hashcode() it will be indeterministic if 2 different objects produce
 * default hashcodes which may or may not determine the same bucket. If by any chance they "point" to the same bucket,
 * you will still be able to find the key if the equals() method says they are equal.
 * If by any chance they "point" to 2 different buckets, you will not find the key even if equals() method says they
 * are equal.
 *
 * hashcode() must be overriden to be consistent with your overridden equals() method.
 * Only in this case it is guaranteed the proper, expected and consistent working of HashMap.
 *
 *
 *
 * The HashMap class in its internal implementation puts objects (keys) in different buckets based on their hashcode.
 * When you query an element or you check if a key is contained in the map, first the proper bucket is looked for
 * based on the hashcode of the queried key. Inside the bucket objects are checked in a sequencial way,
 * and inside a bucket only the equals() method is used to compare elements.
 *
 * So if you do not override Object.hashcode() it will be indeterministic if 2 different objects produce default
 * hashcodes which may or may not determine the same bucket. If by any chance they "point" to the same bucket,
 * you will still be able to find the key if the equals() method says they are equal. If by any chance they "point"
 * to 2 different buckets, you will not find the key even if equals() method says they are equal.
 *
 * hashcode() must be overriden to be consistent with your overridden equals() method. Only in this case it is
 * guaranteed the proper, expected and consistent working of HashMap.
 *
 * Read the javadoc of Object.hashcode() for the contract that you must not violate.
 * The main point is that if equals() returns true for another object, the hashcode() method must return the same
 * value for both of these objects.

 */
public class HashcodeExamples {

    private static final Logger logger = LoggerFactory.getLogger(HashcodeExamples.class);

    public void stringHashcodeExample() {
        String str1 = "";
        String str2 = "hello";
        String str3 = "hello";
        String str4 = "Hello";

        String str5 = str4;

        logger.info("Hash code of empty string: {}", str1.hashCode());
        logger.info("Hash code of {}: {}", str2, str2.hashCode());
        logger.info("Hash code of {}: {}", str3, str3.hashCode());
        logger.info("Hash code of {}: {}", str4, str4.hashCode());
        logger.info("Hash code of {}: {}", str5, str5.hashCode());

        logger.info("\nHash code of Aa: {}", "Aa".hashCode());
        logger.info("Hash code of BB: {}", "BB".hashCode());
    }
}
