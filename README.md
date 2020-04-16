# StateTransition

AC自动机(Aho-Corasick automaton)   
Only used for multi string matching now.   

    <dependency>
      <groupId>date.iterator.automaton</groupId>
      <artifactId>State</artifactId>
      <version>1.0.0</version>
    </dependency>

# e.g.

https://github.com/saaavsaaa/StateTransition/blob/master/src/test/java/date/iterator/state/ACTest.java   
This is the only real unit test in this project.Others are all used to aid coding.

-----

    @Test
    public void testSearchCharacter() {
        Map<String, String> expected = new HashMap<>();
        expected.put("ade", "6,15");
        expected.put("abcde", "0");
        expected.put("abc", "0,11");
        expected.put("dee", "3,7,16");
        expected.put("eade", "5,14");

        String[] ss = new String[] {"abcde", "ade", "abc", "dee", "eade"};
        AC ac = new AC();
        ac.init(ss);
        String input = "abcdeeadeeeabceadee";
        Map<String, String> actual = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            Collection<CharNode> searched = ac.search(input.charAt(i));
            for (CharNode each : searched) {
                for (Integer originLength : each.getOriginLengths()) {
                    int beginIndex = i + 1 - originLength;
                    String achieved = input.substring(beginIndex, i + 1);
                    if (actual.containsKey(achieved)) {
                        String value = actual.get(achieved);
                        value += "," + beginIndex;
                        actual.put(achieved, value);
                    } else {
                        actual.put(achieved, String.valueOf(beginIndex));
                    }
                }
            }
        }
        for (String key : actual.keySet()) {
            String expectedValue = expected.get(key);
            String actualValue = actual.get(key);
            Assert.assertEquals(expectedValue, actualValue);
        }
    }

-----
