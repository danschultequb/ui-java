package qub;

public interface EveryoneUITextTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(EveryoneUIText.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            final Function0<EveryoneUIText> creator = () ->
            {
                final EveryoneSwingUI ui = EveryoneSwingUITests.createUI(mainAsyncRunner);
                return ui.create(EveryoneUIText.class).await();
            };

            EveryoneUIElementTests.test(runner, creator);
            UITextTests.test(runner, creator);

            runner.testGroup("setText(String)", () ->
            {
                runner.test("when value changes", (Test test) ->
                {
                    final EveryoneUIText uiText = creator.run();

                    final BooleanValue triggered = BooleanValue.create();
                    final FakeEveryoneUIElementParent parent = FakeEveryoneUIElementParent.create()
                        .setRepaintAction(() -> triggered.set(true));
                    uiText.setParent(parent);

                    final EveryoneUIText setTextResult = uiText.setText("hello world!");
                    test.assertSame(uiText, setTextResult);
                    test.assertEqual("hello world!", uiText.getText());
                    test.assertTrue(triggered.get());
                });

                runner.test("when value doesn't change", (Test test) ->
                {
                    final EveryoneUIText uiText = creator.run()
                        .setText("abc");

                    final BooleanValue triggered = BooleanValue.create();
                    final FakeEveryoneUIElementParent parent = FakeEveryoneUIElementParent.create()
                        .setRepaintAction(() -> triggered.set(true));
                    uiText.setParent(parent);

                    final EveryoneUIText setTextResult = uiText.setText(uiText.getText());
                    test.assertSame(uiText, setTextResult);
                    test.assertEqual("abc", uiText.getText());
                    test.assertFalse(triggered.hasValue());
                });
            });

            runner.testGroup("setHorizontalAlignment(HorizontalAlignment)", () ->
            {
                runner.test("when value changes", (Test test) ->
                {
                    final EveryoneUIText uiText = creator.run()
                        .setHorizontalAlignment(HorizontalAlignment.Left);

                    final BooleanValue triggered = BooleanValue.create();
                    final FakeEveryoneUIElementParent parent = FakeEveryoneUIElementParent.create()
                        .setRepaintAction(() -> triggered.set(true));
                    uiText.setParent(parent);

                    final EveryoneUIText setHorizontalAlignmentResult = uiText.setHorizontalAlignment(HorizontalAlignment.Right);
                    test.assertSame(uiText, setHorizontalAlignmentResult);
                    test.assertEqual(HorizontalAlignment.Right, uiText.getHorizontalAlignment());
                    test.assertTrue(triggered.get());
                });

                runner.test("when value doesn't change", (Test test) ->
                {
                    final EveryoneUIText uiText = creator.run()
                        .setHorizontalAlignment(HorizontalAlignment.Center);

                    final BooleanValue triggered = BooleanValue.create();
                    final FakeEveryoneUIElementParent parent = FakeEveryoneUIElementParent.create()
                        .setRepaintAction(() -> triggered.set(true));
                    uiText.setParent(parent);

                    final EveryoneUIText setHorizontalAlignmentResult = uiText.setHorizontalAlignment(HorizontalAlignment.Center);
                    test.assertSame(uiText, setHorizontalAlignmentResult);
                    test.assertEqual(HorizontalAlignment.Center, uiText.getHorizontalAlignment());
                    test.assertFalse(triggered.hasValue());
                });
            });

            runner.testGroup("setVerticalAlignment(VerticalAlignment)", () ->
            {
                runner.test("when value changes", (Test test) ->
                {
                    final EveryoneUIText uiText = creator.run()
                        .setVerticalAlignment(VerticalAlignment.Top);

                    final BooleanValue triggered = BooleanValue.create();
                    final FakeEveryoneUIElementParent parent = FakeEveryoneUIElementParent.create()
                        .setRepaintAction(() -> triggered.set(true));
                    uiText.setParent(parent);

                    final EveryoneUIText setVerticalAlignmentResult = uiText.setVerticalAlignment(VerticalAlignment.Bottom);
                    test.assertSame(uiText, setVerticalAlignmentResult);
                    test.assertEqual(VerticalAlignment.Bottom, uiText.getVerticalAlignment());
                    test.assertTrue(triggered.get());
                });

                runner.test("when value doesn't change", (Test test) ->
                {
                    final EveryoneUIText uiText = creator.run()
                        .setVerticalAlignment(VerticalAlignment.Center);

                    final BooleanValue triggered = BooleanValue.create();
                    final FakeEveryoneUIElementParent parent = FakeEveryoneUIElementParent.create()
                        .setRepaintAction(() -> triggered.set(true));
                    uiText.setParent(parent);

                    final EveryoneUIText setVerticalAlignmentResult = uiText.setVerticalAlignment(VerticalAlignment.Center);
                    test.assertSame(uiText, setVerticalAlignmentResult);
                    test.assertEqual(VerticalAlignment.Center, uiText.getVerticalAlignment());
                    test.assertFalse(triggered.hasValue());
                });
            });

            runner.testGroup("paint()", () ->
            {
                final Action2<EveryoneUIText,Iterable<FakeDrawOperation>> paintTest = (EveryoneUIText text, Iterable<FakeDrawOperation> expected) ->
                {
                    runner.test("with " + text, (Test test) ->
                    {
                        final FakeUIPainter painter = FakeUIPainter.create();
                        text.paint(painter);
                        test.assertEqual(expected, painter.getDrawOperations());
                    });
                };

                paintTest.run(
                    creator.run(),
                    Iterable.create());
                paintTest.run(
                    creator.run()
                        .setSize(100, 200),
                    Iterable.create());
                paintTest.run(
                    creator.run()
                        .setBackgroundColor(Color.green),
                    Iterable.create(
                        FakeDrawRectangleOperation.create()
                            .setLineColor(Color.transparent)
                            .setFillColor(Color.green)));
                paintTest.run(
                    creator.run()
                        .setSize(100, 200)
                        .setBackgroundColor(Color.green),
                    Iterable.create(
                        FakeDrawRectangleOperation.create()
                            .setSize(100, 200)
                            .setLineColor(Color.transparent)
                            .setFillColor(Color.green)));
                paintTest.run(
                    creator.run()
                        .setText("Hello"),
                    Iterable.create(
                        FakeDrawTextOperation.create()
                            .setLeftX(0)
                            .setTopY(0)
                            .setText("Hello")
                            .setLineColor(Color.black)));
                paintTest.run(
                    creator.run()
                        .setSize(100, 200)
                        .setBackgroundColor(Color.blue)
                        .setText("Hello"),
                    Iterable.create(
                        FakeDrawRectangleOperation.create()
                            .setSize(100, 200)
                            .setLineColor(Color.transparent)
                            .setFillColor(Color.blue),
                        FakeDrawTextOperation.create()
                            .setLeftX(0)
                            .setTopY(0)
                            .setText("Hello")
                            .setLineColor(Color.black)));
                paintTest.run(
                    creator.run()
                        .setSize(100, 200)
                        .setHorizontalAlignment(HorizontalAlignment.Left)
                        .setText("abc"),
                    Iterable.create(
                        FakeDrawTextOperation.create()
                            .setLeftX(0)
                            .setTopY(0)
                            .setText("abc")
                            .setLineColor(Color.black)));
                paintTest.run(
                    creator.run()
                        .setSize(100, 200)
                        .setHorizontalAlignment(HorizontalAlignment.Center)
                        .setText("abc"),
                    Iterable.create(
                        FakeDrawTextOperation.create()
                            .setCenterX(50)
                            .setTopY(0)
                            .setText("abc")
                            .setLineColor(Color.black)));
                paintTest.run(
                    creator.run()
                        .setSize(100, 200)
                        .setHorizontalAlignment(HorizontalAlignment.Right)
                        .setText("abc"),
                    Iterable.create(
                        FakeDrawTextOperation.create()
                            .setRightX(100)
                            .setTopY(0)
                            .setText("abc")
                            .setLineColor(Color.black)));
                paintTest.run(
                    creator.run()
                        .setSize(100, 200)
                        .setVerticalAlignment(VerticalAlignment.Top)
                        .setText("abc"),
                    Iterable.create(
                        FakeDrawTextOperation.create()
                            .setLeftX(0)
                            .setTopY(0)
                            .setText("abc")
                            .setLineColor(Color.black)));
                paintTest.run(
                    creator.run()
                        .setSize(100, 200)
                        .setVerticalAlignment(VerticalAlignment.Center)
                        .setText("abc"),
                    Iterable.create(
                        FakeDrawTextOperation.create()
                            .setLeftX(0)
                            .setCenterY(100)
                            .setText("abc")
                            .setLineColor(Color.black)));
                paintTest.run(
                    creator.run()
                        .setSize(100, 200)
                        .setVerticalAlignment(VerticalAlignment.Bottom)
                        .setText("abc"),
                    Iterable.create(
                        FakeDrawTextOperation.create()
                            .setLeftX(0)
                            .setBottomY(200)
                            .setText("abc")
                            .setLineColor(Color.black)));
            });
        });
    }
}
