package qub;

public interface EveryoneUIVerticalLayoutTests
{
    public static EveryoneUIVerticalLayout create(EveryoneSwingUI ui)
    {
        return ui.create(EveryoneUIVerticalLayout.class).await();
    }

    public static void test(TestRunner runner)
    {
        runner.testGroup(EveryoneUIVerticalLayout.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            final Function0<EveryoneSwingUI> uiCreator = () ->
            {
                return EveryoneSwingUITests.createUI(mainAsyncRunner);
            };

            final Function0<EveryoneUIVerticalLayout> creator = () ->
            {
                final EveryoneSwingUI ui = uiCreator.run();
                return EveryoneUIVerticalLayoutTests.create(ui);
            };

            EveryoneUIElementTests.test(runner, creator);
            UIVerticalLayoutTests.test(runner, creator);

            runner.testGroup("paint(UIPainter)", () ->
            {
                final Action2<EveryoneUIVerticalLayout,Iterable<FakeDrawOperation>> paintTest = (EveryoneUIVerticalLayout uiLayout, Iterable<FakeDrawOperation> expected) ->
                {
                    runner.test("with " + uiLayout.toString(), (Test test) ->
                    {
                        final FakeUIPainter painter = FakeUIPainter.create();
                        uiLayout.paint(painter);
                        test.assertEqual(expected, painter.getDrawOperations());
                    });
                };

                try (final EveryoneSwingUI ui = uiCreator.run())
                {
                    paintTest.run(
                        EveryoneUIVerticalLayoutTests.create(ui),
                        Iterable.create());

                    paintTest.run(
                        EveryoneUIVerticalLayoutTests.create(ui)
                            .setBackgroundColor(Color.white),
                        Iterable.create(
                            FakeDrawRectangleOperation.create()
                                .setLeftX(0)
                                .setTopY(0)
                                .setWidth(0)
                                .setHeight(0)
                                .setLineColor(Color.transparent)
                                .setFillColor(Color.white)));

                    paintTest.run(
                        EveryoneUIVerticalLayoutTests.create(ui)
                            .setSize(100, 200),
                        Iterable.create());

                    paintTest.run(
                        EveryoneUIVerticalLayoutTests.create(ui)
                            .add(ui.createUIText().await()
                                .setText("abc")),
                        Iterable.create(
                            FakeDrawTextOperation.create()
                                .setLeftX(0)
                                .setTopY(0)
                                .setText("abc")
                                .setLineColor(Color.black)));

                    paintTest.run(
                        EveryoneUIVerticalLayoutTests.create(ui)
                            .setSize(500, 500)
                            .setHorizontalAlignment(HorizontalAlignment.Left)
                            .add(ui.createUIText().await()
                                .setSize(100, 100)
                                .setText("abc")),
                        Iterable.create(
                            FakeDrawTextOperation.create()
                                .setLeftX(0)
                                .setTopY(0)
                                .setText("abc")
                                .setLineColor(Color.black)));
                    paintTest.run(
                        EveryoneUIVerticalLayoutTests.create(ui)
                            .setSize(500, 500)
                            .setHorizontalAlignment(HorizontalAlignment.Center)
                            .add(ui.createUIText().await()
                                .setSize(100, 100)
                                .setText("abc")),
                        Iterable.create(
                            FakeDrawTextOperation.create()
                                .setTransform(Transform2.create().translateX(200))
                                .setLeftX(0)
                                .setTopY(0)
                                .setText("abc")
                                .setLineColor(Color.black)));
                    paintTest.run(
                        EveryoneUIVerticalLayoutTests.create(ui)
                            .setSize(500, 500)
                            .setHorizontalAlignment(HorizontalAlignment.Right)
                            .add(ui.createUIText().await()
                                .setSize(100, 100)
                                .setText("abc")),
                        Iterable.create(
                            FakeDrawTextOperation.create()
                                .setTransform(Transform2.create().translateX(400))
                                .setLeftX(0)
                                .setTopY(0)
                                .setText("abc")
                                .setLineColor(Color.black)));

                    paintTest.run(
                        EveryoneUIVerticalLayoutTests.create(ui)
                            .add(ui.createUIButton().await()
                                .setBackgroundColor(Color.green)
                                .setText("Hello World!")
                                .setSize(100, 50))
                            .add(ui.createUIText().await()
                                .setText("abc")
                                .setSize(200, 100)
                                .setBackgroundColor(Color.blue)),
                        Iterable.create(
                            FakeDrawRectangleOperation.create()
                                .setSize(100, 50)
                                .setLineColor(Color.transparent)
                                .setFillColor(Color.green),
                            FakeDrawRectangleOperation.create()
                                .setSize(100, 50)
                                .setLineColor(Color.black)
                                .setFillColor(Color.transparent),
                            FakeDrawTextOperation.create()
                                .setCenterX(50)
                                .setCenterY(25)
                                .setText("Hello World!")
                                .setLineColor(Color.black),
                            FakeDrawRectangleOperation.create()
                                .translateY(50)
                                .setSize(200, 100)
                                .setLineColor(Color.transparent)
                                .setFillColor(Color.blue),
                            FakeDrawTextOperation.create()
                                .translateY(50)
                                .setLeftX(0)
                                .setTopY(0)
                                .setText("abc")
                                .setLineColor(Color.black)));

                    paintTest.run(
                        EveryoneUIVerticalLayoutTests.create(ui)
                            .setBackgroundColor(Color.red)
                            .add(ui.createUIButton().await()
                                .setBackgroundColor(Color.green)
                                .setText("Hello World!")
                                .setSize(100, 50))
                            .add(ui.createUIText().await()
                                .setText("abc")
                                .setSize(200, 100)
                                .setBackgroundColor(Color.blue)),
                        Iterable.create(
                            FakeDrawRectangleOperation.create()
                                .setSize(200, 150)
                                .setLineColor(Color.transparent)
                                .setFillColor(Color.red),
                            FakeDrawRectangleOperation.create()
                                .setSize(100, 50)
                                .setLineColor(Color.transparent)
                                .setFillColor(Color.green),
                            FakeDrawRectangleOperation.create()
                                .setSize(100, 50)
                                .setLineColor(Color.black)
                                .setFillColor(Color.transparent),
                            FakeDrawTextOperation.create()
                                .setCenterX(50)
                                .setCenterY(25)
                                .setText("Hello World!")
                                .setLineColor(Color.black),
                            FakeDrawRectangleOperation.create()
                                .translateY(50)
                                .setSize(200, 100)
                                .setLineColor(Color.transparent)
                                .setFillColor(Color.blue),
                            FakeDrawTextOperation.create()
                                .translateY(50)
                                .setLeftX(0)
                                .setTopY(0)
                                .setText("abc")
                                .setLineColor(Color.black)));
                }
            });
        });
    }
}
