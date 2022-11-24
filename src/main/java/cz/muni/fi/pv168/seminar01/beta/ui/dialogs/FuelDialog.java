package cz.muni.fi.pv168.seminar01.beta.ui.dialogs;

import cz.muni.fi.pv168.seminar01.beta.model.FuelPrice;
import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.UIUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class FuelDialog extends DialogBase {
    private JTextField diesel;
    private JTextField gasoline;
    private JTextField lpg;
    private JTextField cng;
    private JTextField electricity;
    private FuelPrice fuelPrice;

    public FuelDialog(Frame frame) {
        super(frame, "Ceny paliv", MainWindow.getFuelPrice());
        // MainWindow.getFuelPrice() is temporary solution, check MainWindow for more info
    }

    public void initializeBottom(JPanel bottom) {
        JButton setButton = new JButton("Zadat");
        onSetButton(setButton);
        UIUtilities.formatDefaultComponent(setButton);
        UIUtilities.formatBeigeTextBrownDialog(setButton);
        bottom.add(setButton);
    }

    public void initializeCenter(JPanel center) {
        setAttributes();
        center.setLayout(new GridLayout(5, 2));
        UIUtilities.formatWhiteTextBrownDialog(center);
        center.add(new JLabel("•  Nafta (litr)"));
        center.add(diesel);
        center.add(new JLabel("•  Benzín (litr)"));
        center.add(gasoline);
        center.add(new JLabel("•  LPG (litr)"));
        center.add(lpg);
        center.add(new JLabel("•  CNG (litr)"));
        center.add(cng);
        center.add(new JLabel("•  Elektřina (kWh)"));
        center.add(electricity);
        this.add(center);
        setSize(330, 220);
    }

    @Override
    protected void addAttribute(Object attribute) {
        fuelPrice = (FuelPrice) attribute;
    }

    public void setAttributes() {
        diesel = UIUtilities.createTextField();
        gasoline = UIUtilities.createTextField();
        lpg = UIUtilities.createTextField();
        cng = UIUtilities.createTextField();
        electricity = UIUtilities.createTextField();

        diesel.setText(fuelPrice.getDieselPriceString());
        gasoline.setText(fuelPrice.getGasolinePriceString());
        lpg.setText(fuelPrice.getLPGPriceString());
        cng.setText(fuelPrice.getCNGPriceString());
        electricity.setText(fuelPrice.getElectricityPriceString());
    }

    public void onSetButton(JButton setButton) {
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fuelPrice.setDieselPrice(new BigDecimal(diesel.getText()));
                    fuelPrice.setGasolinePrice(new BigDecimal(gasoline.getText()));
                    fuelPrice.setLPGPrice(new BigDecimal(lpg.getText()));
                    fuelPrice.setCNGPrice(new BigDecimal(cng.getText()));
                    fuelPrice.setElectricityPrice(new BigDecimal(electricity.getText()));
                    dispose();
                } catch (IllegalArgumentException exception) {
                    new ErrorDialog(MainWindow.getFrame(), exception.getMessage());
                }
            }
        });
    }
}
