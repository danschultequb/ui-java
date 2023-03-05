package qub;

public interface EveryoneUIButtonTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(EveryoneUIButton.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            final Function0<EveryoneUIButton> creator = () ->
            {
                final EveryoneSwingUI ui = EveryoneSwingUITests.createUI(mainAsyncRunner);
                return ui.create(EveryoneUIButton.class).await();
            };

            EveryoneUIElementTests.test(runner, creator);
            UIButtonTests.test(runner, creator);

            runner.testGroup("setText(String)", () ->
            {
                runner.test("when value changes", (Test test) ->
                {
                    final EveryoneUIButton uiText = creator.run();

                    final BooleanValue triggered = BooleanValue.create();
                    final FakeEveryoneUIElementParent parent = FakeEveryoneUIElementParent.create()
                        .setRepaintAction(() -> triggered.set(true));
                    uiText.setParent(parent);

                    final EveryoneUIButton setTextResult = uiText.setText("hello world!");
                    test.assertSame(uiText, setTextResult);
                    test.assertEqual("hello world!", uiText.getText());
                    test.assertTrue(triggered.get());
                });

                runner.test("when value doesn't change", (Test test) ->
                {
                    final EveryoneUIButton uiText = creator.run()
                        .setText("abc");

                    final BooleanValue triggered = BooleanValue.create();
                    final FakeEveryoneUIElementParent parent = FakeEveryoneUIElementParent.create()
                        .setRepaintAction(() -> triggered.set(true));
                    uiText.setParent(parent);

                    final EveryoneUIButton setTextResult = uiText.setText(uiText.getText());
                    test.assertSame(uiText, setTextResult);
                    test.assertEqual("abc", uiText.getText());
                    test.assertFalse(triggered.hasValue());
                });
            });

            runner.testGroup("paint(UIPainter)", () ->
            {
                final Action2<EveryoneUIButton,Iterable<FakeDrawOperation>> paintTest = (EveryoneUIButton uiButton, Iterable<FakeDrawOperation> expected) ->
                {
                    runner.test("with " + uiButton.toString(), (Test test) ->
                    {
                        final FakeUIPainter painter = FakeUIPainter.create();
                        uiButton.paint(painter);
                        test.assertEqual(expected, painter.getDrawOperations());
                    });
                };

                paintTest.run(
                    creator.run(),
                    Iterable.create(
                        FakeDrawRectangleOperation.create()
                            .setLeftX(0)
                            .setTopY(0)
                            .setWidth(0)
                            .setHeight(0)
                            .setLineColor(Color.black)
                            .setFillColor(Color.transparent)));
                paintTest.run(
                    creator.run()
                        .setSize(100, 200),
                    Iterable.create(
                        FakeDrawRectangleOperation.create()
                            .setLeftX(0)
                            .setTopY(0)
                            .setWidth(100)
                            .setHeight(200)
                            .setLineColor(Color.black)
                            .setFillColor(Color.transparent)));
                paintTest.run(
                    creator.run()
                        .setSize(100, 200)
                        .setText("Hello"),
                    Iterable.create(
                        FakeDrawRectangleOperation.create()
                            .setLeftX(0)
                            .setTopY(0)
                            .setWidth(100)
                            .setHeight(200)
                            .setLineColor(Color.black)
                            .setFillColor(Color.transparent),
                        FakeDrawTextOperation.create()
                            .setCenterX(50)
                            .setCenterY(100)
                            .setLineColor(Color.black)
                            .setText("Hello")));
                paintTest.run(
                    creator.run()
                        .setSize(100, 200)
                        .setText("Hello")
                        .setBackgroundColor(Color.blue),
                    Iterable.create(
                        FakeDrawRectangleOperation.create()
                            .setLeftX(0)
                            .setTopY(0)
                            .setWidth(100)
                            .setHeight(200)
                            .setLineColor(Color.transparent)
                            .setFillColor(Color.blue),
                        FakeDrawRectangleOperation.create()
                            .setLeftX(0)
                            .setTopY(0)
                            .setWidth(100)
                            .setHeight(200)
                            .setLineColor(Color.black)
                            .setFillColor(Color.transparent),
                        FakeDrawTextOperation.create()
                            .setCenterX(50)
                            .setCenterY(100)
                            .setLineColor(Color.black)
                            .setText("Hello")));
            });
        });
    }
}
