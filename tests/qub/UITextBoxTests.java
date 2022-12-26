package qub;

public interface UITextBoxTests
{
    static void test(TestRunner runner, Function0<? extends UITextBox> creator)
    {
        PreCondition.assertNotNull(runner, "runner");
        PreCondition.assertNotNull(creator, "creator");

        runner.testGroup(UITextBox.class, () ->
        {
            UIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final UITextBox textBox = creator.run();

                    final UITextBox setBackgroundColorResult = textBox.setBackgroundColor(Color.red);
                    test.assertSame(textBox, setBackgroundColorResult);
                    test.assertEqual(Color.red, textBox.getBackgroundColor());
                });
            });

//            runner.testGroup("setWidth(Distance)", () ->
//            {
//                runner.test("should return " + Types.getTypeName(UITextBox.class), (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UITextBox textBox = creator.run(process);
//                        final UITextBox setWidthResult = textBox.setWidth(Distance.inches(1));
//                        test.assertSame(textBox, setWidthResult);
//                    }
//                });
//            });
//
//            runner.testGroup("setHeight(Distance)", () ->
//            {
//                runner.test("should return " + Types.getTypeName(UITextBox.class), (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UITextBox textBox = creator.run(process);
//                        final UITextBox setHeightResult = textBox.setHeight(Distance.inches(1));
//                        test.assertSame(textBox, setHeightResult);
//                    }
//                });
//            });
//
//            runner.testGroup("setSize(Size2D)", () ->
//            {
//                runner.test("should return " + Types.getTypeName(UITextBox.class), (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UITextBox textBox = creator.run(process);
//                        final UITextBox setHeightResult = textBox.setSize(Size2D.create(Distance.inches(2), Distance.inches(3)));
//                        test.assertSame(textBox, setHeightResult);
//                    }
//                });
//            });
//
//            runner.testGroup("setSize(Distance,Distance)", () ->
//            {
//                runner.test("should return " + Types.getTypeName(UITextBox.class), (Test test) ->
//                {
//                    try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                    {
//                        final UITextBox textBox = creator.run(process);
//                        final UITextBox setHeightResult = textBox.setSize(Distance.inches(2), Distance.inches(3));
//                        test.assertSame(textBox, setHeightResult);
//                    }
//                });
//            });

            runner.testGroup("setText(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UITextBox textBox = creator.run();

                    test.assertThrows(() -> textBox.setText(null),
                        new PreConditionFailure("text cannot be null."));

                    test.assertEqual("", textBox.getText());
                });

                runner.test("with empty", (Test test) ->
                {
                    final UITextBox textBox = creator.run();

                    final UITextBox setTextResult = textBox.setText("");
                    test.assertSame(textBox, setTextResult);

                    test.assertEqual("", textBox.getText());
                });

                runner.test("with non-empty", (Test test) ->
                {
                    final UITextBox textBox = creator.run();

                    final UITextBox setTextResult = textBox.setText("hello");
                    test.assertSame(textBox, setTextResult);

                    test.assertEqual("hello", textBox.getText());
                });
            });

            runner.testGroup("insertText(int,String)", () ->
            {
                final Action4<String,Integer,String,Throwable> insertTextErrorTest = (String originalText, Integer startIndex, String text, Throwable expected) ->
                {
                    runner.test("with " + English.andList("original text " + Strings.escapeAndQuote(originalText), startIndex, Strings.escapeAndQuote(text)), (Test test) ->
                    {
                        final UITextBox textBox = creator.run().setText(originalText);

                        test.assertThrows(() -> textBox.insertText(startIndex, text),
                            expected);

                        test.assertEqual(originalText, textBox.getText());
                    });
                };

                insertTextErrorTest.run("", -1, "hello", new PreConditionFailure("startIndex (-1) must be equal to 0."));
                insertTextErrorTest.run("", 1, "hello", new PreConditionFailure("startIndex (1) must be equal to 0."));
                insertTextErrorTest.run("abc", -1, "hello", new PreConditionFailure("startIndex (-1) must be between 0 and 3."));
                insertTextErrorTest.run("abc", 5, "hello", new PreConditionFailure("startIndex (5) must be between 0 and 3."));

                final Action4<String,Integer,String,String> insertTextTest = (String previousText, Integer startIndex, String text, String expectedNewText) ->
                {
                    runner.test("with " + English.andList("original text " + Strings.escapeAndQuote(previousText), startIndex, Strings.escapeAndQuote(text)), (Test test) ->
                    {
                        final UITextBox textBox = creator.run().setText(previousText);

                        textBox.onTextChanged((TextChange textChange) ->
                        {
                            test.assertNotNull(textChange);
                            test.assertEqual(TextChangeType.Insertion, textChange.getChangeType());
                            test.assertFalse(textChange.isRemoval());
                            test.assertTrue(textChange.isInsertion());
                            test.assertEqual(startIndex, textChange.getStartIndex());
                            test.assertEqual(text, textChange.getInsertedText());
                            test.assertEqual(text.length(), textChange.getLength());
                            test.assertEqual(startIndex + text.length(), textChange.getAfterEndIndex());
                            test.assertEqual(previousText, textChange.getPreviousText());
                            test.assertEqual(expectedNewText, textChange.getNewText());
                        });

                        final UITextBox insertTextResult = textBox.insertText(startIndex, text);
                        test.assertSame(textBox, insertTextResult);
                        test.assertEqual(expectedNewText, textBox.getText());
                    });
                };

                insertTextTest.run("", 0, "abc", "abc");
                insertTextTest.run("def", 0, "abc", "abcdef");
                insertTextTest.run("abf", 2, "cde", "abcdef");
                insertTextTest.run("abc", 3, "def", "abcdef");
            });

            runner.testGroup("removeText(int,int)", () ->
            {
                final Action4<String,Integer,Integer,Throwable> removeTextErrorTest = (String originalText, Integer startIndex, Integer length, Throwable expected) ->
                {
                    runner.test("with " + English.andList("original text " + Strings.escapeAndQuote(originalText), startIndex, length), (Test test) ->
                    {
                        final UITextBox textBox = creator.run().setText(originalText);

                        test.assertThrows(() -> textBox.removeText(startIndex, length),
                            expected);

                        test.assertEqual(originalText, textBox.getText());
                    });
                };

                removeTextErrorTest.run("", -1, 1, new PreConditionFailure("startIndex (-1) must be equal to 0."));
                removeTextErrorTest.run("", 1, 1, new PreConditionFailure("startIndex (1) must be equal to 0."));
                removeTextErrorTest.run("abc", -1, 1, new PreConditionFailure("startIndex (-1) must be between 0 and 2."));
                removeTextErrorTest.run("abc", 5, 1, new PreConditionFailure("startIndex (5) must be between 0 and 2."));

                final Action4<String,Integer,Integer,String> removeTextTest = (String previousText, Integer startIndex, Integer length, String expectedNewText) ->
                {
                    runner.test("with " + English.andList("original text " + Strings.escapeAndQuote(previousText), startIndex, length), (Test test) ->
                    {
                        final UITextBox textBox = creator.run().setText(previousText);

                        textBox.onTextChanged((TextChange textChange) ->
                        {
                            test.assertNotNull(textChange);
                            test.assertEqual(TextChangeType.Removal, textChange.getChangeType());
                            test.assertTrue(textChange.isRemoval());
                            test.assertFalse(textChange.isInsertion());
                            test.assertEqual(startIndex, textChange.getStartIndex());
                            test.assertThrows(() -> textChange.getInsertedText(),
                                new PreConditionFailure("this.isInsertion() cannot be false."));
                            test.assertEqual(length, textChange.getLength());
                            test.assertEqual(startIndex + length, textChange.getAfterEndIndex());
                            test.assertThrows(() -> textChange.getPreviousText(),
                                new PreConditionFailure("this.isInsertion() cannot be false."));
                            test.assertEqual(expectedNewText, textChange.getNewText());
                        });

                        final UITextBox removeTextResult = textBox.removeText(startIndex, length);
                        test.assertSame(textBox, removeTextResult);
                        test.assertEqual(expectedNewText, textBox.getText());
                    });
                };

                removeTextTest.run("abcdef", 0, 0, "abcdef");
                removeTextTest.run("abcdef", 0, 1, "bcdef");
                removeTextTest.run("abcdef", 1, 2, "adef");
                removeTextTest.run("abcdef", 2, 4, "ab");
            });

            runner.testGroup("onTextChanged(Action0)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UITextBox textBox = creator.run();
                    test.assertEqual("", textBox.getText());

                    test.assertThrows(() -> textBox.onTextChanged((Action0)null),
                        new PreConditionFailure("callback cannot be null."));
                });

                runner.test("with one registration", (Test test) ->
                {
                    final UITextBox textBox = creator.run();
                    test.assertEqual("", textBox.getText());

                    final List<String> newTextBoxTexts = List.create();
                    final Disposable registration = textBox.onTextChanged(() ->
                    {
                        final String newTextBoxText = textBox.getText();
                        newTextBoxTexts.add(newTextBoxText);
                    });
                    test.assertNotNull(registration);

                    textBox.setText("hello 1");
                    test.assertEqual("hello 1", textBox.getText());
                    test.assertEqual(Iterable.create("hello 1"), newTextBoxTexts);
                });

                runner.test("with two registrations", (Test test) ->
                {
                    final UITextBox textBox = creator.run();
                    test.assertEqual("", textBox.getText());

                    final List<String> values = List.create();
                    final Disposable registration1 = textBox.onTextChanged(() ->
                    {
                        final String newTextBoxText = textBox.getText();
                        values.add(newTextBoxText + "1");
                    });
                    test.assertNotNull(registration1);

                    final Disposable registration2 = textBox.onTextChanged(() ->
                    {
                        final String newTextBoxText = textBox.getText();
                        values.add(newTextBoxText + "2");
                    });
                    test.assertNotNull(registration2);

                    textBox.setText("hello");
                    test.assertEqual("hello", textBox.getText());
                    test.assertTrue(Set.create("hello2", "hello1").equals(Set.create(values)));
                });
            });

            runner.testGroup("onTextChanged(Action1<TextChange>)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UITextBox textBox = creator.run();
                    test.assertEqual("", textBox.getText());

                    test.assertThrows(() -> textBox.onTextChanged((Action1<TextChange>)null),
                        new PreConditionFailure("callback cannot be null."));
                });

                runner.test("with one registration", (Test test) ->
                {
                    final UITextBox textBox = creator.run();
                    test.assertEqual("", textBox.getText());

                    final List<String> newTextBoxTexts = List.create();
                    final Disposable registration = textBox.onTextChanged((TextChange textChange) ->
                    {
                        test.assertNotNull(textChange);

                        newTextBoxTexts.add(textChange.getNewText());

                        test.assertEqual(TextChangeType.Insertion, textChange.getChangeType());
                        test.assertTrue(textChange.isInsertion());
                        test.assertFalse(textChange.isRemoval());
                        test.assertEqual("", textChange.getPreviousText());
                        test.assertEqual(0, textChange.getStartIndex());
                        test.assertEqual("hello 1".length(), textChange.getAfterEndIndex());
                        test.assertEqual("hello 1".length(), textChange.getLength());
                        test.assertEqual("hello 1", textChange.getInsertedText());
                        test.assertEqual("hello 1", textChange.getNewText());
                    });
                    test.assertNotNull(registration);

                    textBox.setText("hello 1");
                    test.assertEqual("hello 1", textBox.getText());
                    test.assertEqual(Iterable.create("hello 1"), newTextBoxTexts);
                });

                runner.test("with two registrations", (Test test) ->
                {
                    final UITextBox textBox = creator.run();
                    test.assertEqual("", textBox.getText());

                    final List<String> values = List.create();
                    final Disposable registration1 = textBox.onTextChanged((TextChange textChange) ->
                    {
                        values.add(textChange.getNewText() + "1");
                    });
                    test.assertNotNull(registration1);

                    final Disposable registration2 = textBox.onTextChanged((TextChange textChange) ->
                    {
                        values.add(textChange.getNewText() + "2");
                    });
                    test.assertNotNull(registration2);

                    textBox.setText("hello");
                    test.assertEqual("hello", textBox.getText());
                    test.assertTrue(Set.create("hello2", "hello1").equals(Set.create(values)));
                });
            });

//            runner.testGroup("setFontSize(Distance)", () ->
//            {
//                final Action2<Distance,Throwable> setFontSizeErrorTest = (Distance fontSize, Throwable expected) ->
//                {
//                    runner.test("with " + fontSize, (Test test) ->
//                    {
//                        try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                        {
//                            final UITextBox textBox = creator.run(process);
//                            test.assertThrows(() -> textBox.setFontSize(fontSize), expected);
//                        }
//                    });
//                };
//
//                setFontSizeErrorTest.run(null, new PreConditionFailure("fontSize cannot be null."));
//                setFontSizeErrorTest.run(Distance.inches(-1), new PreConditionFailure("fontSize (-1.0 Inches) must be greater than or equal to 0.0 Inches."));
//
//                final Action1<Distance> setFontSizeTest = (Distance fontSize) ->
//                {
//                    runner.test("with " + fontSize, (Test test) ->
//                    {
//                        try (final FakeDesktopProcess process = FakeDesktopProcess.create())
//                        {
//                            final UITextBox textBox = creator.run(process);
//                            final UITextBox setFontSizeResult = textBox.setFontSize(fontSize);
//                            test.assertSame(textBox, setFontSizeResult);
//                            test.assertEqual(fontSize, textBox.getFontSize());
//                        }
//                    });
//                };
//
//                setFontSizeTest.run(Distance.zero);
//                setFontSizeTest.run(Distance.fontPoints(12));
//                setFontSizeTest.run(Distance.inches(1));
//            });
        });
    }
}
