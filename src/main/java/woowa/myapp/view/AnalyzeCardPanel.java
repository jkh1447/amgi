package woowa.myapp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.AbstractDataset;
import woowa.myapp.controller.MainController;
import woowa.myapp.model.Card;
import woowa.myapp.model.Deck;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class AnalyzeCardPanel extends JPanel {

    private MainController mainController;

    private BarRenderer renderer;
    private CategoryPlot plot;
    private DefaultCategoryDataset cardsDataset;
    private JFreeChart difficultyChart;
    private ChartPanel cardsChartPanel;

    private List<Card> topCards;

    private final String CHART_FONT = "SansSerif";

    public AnalyzeCardPanel(Deck deck, MainController mainController) {

        this.mainController = mainController;

        setLayout(new GridBagLayout());

        cardsDataset = createCardsDataset(deck);
        difficultyChart = ChartFactory.createBarChart(
                deck.getName(),
                "Card",
                "difficulty",
                cardsDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chartInit();

        plot = difficultyChart.getCategoryPlot();
        renderer = (BarRenderer) plot.getRenderer();
        rendererInit();

        cardsChartPanel = new ChartPanel(difficultyChart);
        chartPanelInit();

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        add(cardsChartPanel, c);

        // 메인 버튼
        JButton mainButton = new JButton("메인으로");
        mainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMainButtonEvent(mainButton);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(15, 0, 0, 0);
        add(mainButton, c);
    }

    public DefaultCategoryDataset createCardsDataset(Deck deck) {
        int topN = 10;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        topCards = deck.getCards().stream()
                .filter(c->c.getDifficulty() > 0)
                .sorted((c1, c2) -> Double.compare(c2.getDifficulty(), c1.getDifficulty()))
                .limit(topN)
                .collect(Collectors.toList());

        for (Card c : topCards) {
            String front = c.getFront();
            if (front.length() > 10) {
                front = front.substring(0, 10);
            }
            dataset.addValue(c.getDifficulty(), "Difficulty", front);
        }

        return dataset;
    }

    void addMainButtonEvent(JButton mainButton) {
        mainButton.addActionListener(e -> {
            mainController.getMainButtonEvent();
        });
    }

    void chartPanelInit() {
        javax.swing.ToolTipManager.sharedInstance().registerComponent(cardsChartPanel);
        cardsChartPanel.setPreferredSize(new java.awt.Dimension(700, 400));
        cardsChartPanel.setMaximumSize(new java.awt.Dimension(700, 400));
        cardsChartPanel.setDisplayToolTips(true);

    }

    void rendererInit() {
        renderer.setMaximumBarWidth(0.05);
        renderer.setMinimumBarLength(0.05);
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());
        renderer.setShadowVisible(false);

        renderer.setDefaultToolTipGenerator((CategoryDataset dataset, int row, int column) -> {
            Card card = topCards.get(column);
            return "<html>앞면: " + card.getFront() + "<br/>뒷면: " + card.getBack() + "</html>";
        });
    }

    void chartInit() {
        // 폰트 설정
        difficultyChart.getCategoryPlot().getDomainAxis().setLabelFont(new Font(CHART_FONT, Font.BOLD, 14)); // X축 레이블
        difficultyChart.getCategoryPlot().getDomainAxis().setTickLabelFont(new Font(CHART_FONT, Font.PLAIN, 12)); // X축 눈금
        difficultyChart.getCategoryPlot().getRangeAxis().setLabelFont(new Font(CHART_FONT, Font.BOLD, 14)); // Y축 레이블
        difficultyChart.getCategoryPlot().getRangeAxis().setTickLabelFont(new Font(CHART_FONT, Font.PLAIN, 12)); // Y축 눈금
        difficultyChart.getTitle().setFont(new Font(CHART_FONT, Font.BOLD, 16));
        if (difficultyChart.getLegend() != null) {
            difficultyChart.getLegend().setItemFont(new Font(CHART_FONT, Font.PLAIN, 12));
        }

        // 색 설정
        difficultyChart.setBackgroundPaint(Color.GRAY);
        Color defaultBg = UIManager.getColor("Panel.background");
        difficultyChart.setBackgroundPaint(defaultBg);
        difficultyChart.getCategoryPlot().setBackgroundPaint(defaultBg);
        difficultyChart.getLegend().setBackgroundPaint(defaultBg);
    }
}
