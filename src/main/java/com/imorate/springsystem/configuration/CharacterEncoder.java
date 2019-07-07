package com.imorate.springsystem.configuration;

import org.springframework.stereotype.Component;

@Component
public class CharacterEncoder {

    private String beforeEncode;
    private String afterEncode;
    private char splitter;

    public CharacterEncoder() {
    }

    public CharacterEncoder(CharacterEncoderBuilder builder) {
        this.beforeEncode = builder.beforeEncode;
        this.afterEncode = builder.afterEncode;
        this.splitter = builder.splitter;
    }

    public String getAfterEncode() {
        return afterEncode;
    }

    public static class CharacterEncoderBuilder {

        private String beforeEncode;
        private String afterEncode;
        private char splitter;

        public CharacterEncoderBuilder(String beforeEncode, char splitter) {
            this.beforeEncode = beforeEncode;
            this.splitter = splitter;
        }

        public CharacterEncoderBuilder withString(String beforeEncode) {
            this.beforeEncode = beforeEncode;
            return this;
        }

        public CharacterEncoderBuilder withSplitter(char splitter) {
            this.splitter = splitter;
            return this;
        }

        public CharacterEncoder build() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < this.beforeEncode.length(); i++) {
                char character = this.beforeEncode.charAt(i);
                switch (character) {
                    case ('0'):
                        stringBuilder.append('۰');
                        break;
                    case ('1'):
                        stringBuilder.append('١');
                        break;
                    case ('2'):
                        stringBuilder.append('۲');
                        break;
                    case ('3'):
                        stringBuilder.append('۳');
                        break;
                    case ('4'):
                        stringBuilder.append('۴');
                        break;
                    case ('5'):
                        stringBuilder.append('۵');
                        break;
                    case ('6'):
                        stringBuilder.append('۶');
                        break;
                    case ('7'):
                        stringBuilder.append('۷');
                        break;
                    case ('8'):
                        stringBuilder.append('۸');
                        break;
                    case ('9'):
                        stringBuilder.append('۹');
                        break;
                    default:
                        stringBuilder.append(this.splitter);
                }
            }
            this.afterEncode = stringBuilder.toString();
            return new CharacterEncoder(this);
        }

    }

}