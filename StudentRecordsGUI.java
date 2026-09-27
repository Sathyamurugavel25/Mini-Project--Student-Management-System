import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StudentRecordsGUI extends JFrame {

    // Backend data
    HashMap<Integer, StudentRecords1> map = new HashMap<>();

    // UI components
    JTable table;
    DefaultTableModel model;

    JTextField nameField;
    JTextField addressField;
    JTextField phoneField;
    JTextField mark1Field;
    JTextField mark2Field;
    JTextField mark3Field;
    JTextField searchField;

    JLabel totalStudentsLabel;
    JLabel averageLabel;
    JLabel topGradeLabel;

    int startingPt = 100;

    // Colors
    Color sidebarColor = new Color(30, 35, 45);
    Color backgroundColor = new Color(245, 247, 250);
    Color cardColor = Color.WHITE;
    Color accentColor = new Color(55, 110, 220);
    Color textColor = new Color(35, 40, 50);
    Color secondaryColor = new Color(110, 118, 130);

    Font titleFont = new Font("SansSerif", Font.BOLD, 26);
    Font headingFont = new Font("SansSerif", Font.BOLD, 20);
    Font normalFont = new Font("SansSerif", Font.PLAIN, 14);
    Font boldFont = new Font("SansSerif", Font.BOLD, 14);

    public StudentRecordsGUI() {

        setTitle("Student Management System");
        setSize(1250, 750);
        setMinimumSize(new Dimension(1000, 650));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        // Sidebar
        mainPanel.add(createSidebar(), BorderLayout.WEST);

        // Main content
        mainPanel.add(createMainContent(), BorderLayout.CENTER);

        add(mainPanel);
    }

    // ============================================================
    // SIDEBAR
    // ============================================================

    JPanel createSidebar() {

        JPanel sidebar = new JPanel();

        sidebar.setPreferredSize(new Dimension(215, 0));
        sidebar.setBackground(sidebarColor);

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        sidebar.setBorder(
                new EmptyBorder(25, 18, 25, 18)
        );

        JLabel logo = new JLabel("🎓  STUDENT");
        logo.setForeground(Color.WHITE);
        logo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        19
                )
        );

        JLabel subtitle = new JLabel("MANAGEMENT SYSTEM");
        subtitle.setForeground(
                new Color(160, 166, 175)
        );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(3));
        sidebar.add(subtitle);

        sidebar.add(
                Box.createVerticalStrut(45)
        );

        JButton dashboard =
                createSideButton("▣   Dashboard");

        JButton addStudent =
                createSideButton("＋   Add Student");

        JButton search =
                createSideButton("⌕   Search Student");

        JButton records =
                createSideButton("☷   All Records");

        JButton delete =
                createSideButton("⌫   Delete Student");

        dashboard.setBackground(accentColor);

        sidebar.add(dashboard);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(addStudent);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(search);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(records);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(delete);

        // Button actions

        dashboard.addActionListener(e -> {
            refreshTable();
            updateStatistics();
        });

        addStudent.addActionListener(e ->
                showAddStudentDialog()
        );

        search.addActionListener(e ->
                searchStudent()
        );

        records.addActionListener(e ->
                refreshTable()
        );

        delete.addActionListener(e ->
                deleteStudent()
        );

        sidebar.add(Box.createVerticalGlue());

        JLabel footer =
                new JLabel("Student Records v1.0");

        footer.setForeground(
                new Color(125, 130, 140)
        );

        footer.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        sidebar.add(footer);

        return sidebar;
    }

    JButton createSideButton(String text) {

        JButton button = new JButton(text);

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setMaximumSize(
                new Dimension(180, 45)
        );

        button.setPreferredSize(
                new Dimension(180, 45)
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setForeground(Color.WHITE);
        button.setBackground(sidebarColor);

        button.setFont(normalFont);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        15,
                        10,
                        10
                )
        );

        return button;
    }

    // ============================================================
    // MAIN CONTENT
    // ============================================================

    JPanel createMainContent() {

        JPanel content = new JPanel(
                new BorderLayout()
        );

        content.setBackground(
                backgroundColor
        );

        content.setBorder(
                new EmptyBorder(
                        25,
                        25,
                        25,
                        25
                )
        );

        // Header
        JPanel header = createHeader();

        content.add(
                header,
                BorderLayout.NORTH
        );

        // Center
        JPanel center = new JPanel(
                new BorderLayout()
        );

        center.setBackground(
                backgroundColor
        );

        // Statistics
        JPanel statistics =
                createStatistics();

        center.add(
                statistics,
                BorderLayout.NORTH
        );

        // Table
        JPanel tablePanel =
                createTablePanel();

        center.add(
                tablePanel,
                BorderLayout.CENTER
        );

        content.add(
                center,
                BorderLayout.CENTER
        );

        return content;
    }

    // ============================================================
    // HEADER
    // ============================================================

    JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                backgroundColor
        );

        header.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        25,
                        0
                )
        );

        JPanel left =
                new JPanel();

        left.setBackground(
                backgroundColor
        );

        left.setLayout(
                new BoxLayout(
                        left,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "Student Dashboard"
                );

        title.setFont(titleFont);
        title.setForeground(textColor);

        JLabel subtitle =
                new JLabel(
                        "Manage student records, marks and grades"
                );

        subtitle.setFont(normalFont);
        subtitle.setForeground(
                secondaryColor
        );

        left.add(title);

        left.add(
                Box.createVerticalStrut(5)
        );

        left.add(subtitle);

        JButton add =
                new JButton("+  Add Student");

        add.setBackground(
                accentColor
        );

        add.setForeground(Color.WHITE);

        add.setFont(boldFont);

        add.setFocusPainted(false);

        add.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );

        add.addActionListener(e ->
                showAddStudentDialog()
        );

        header.add(
                left,
                BorderLayout.WEST
        );

        header.add(
                add,
                BorderLayout.EAST
        );

        return header;
    }

    // ============================================================
    // STATISTICS
    // ============================================================

    JPanel createStatistics() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        panel.setBackground(
                backgroundColor
        );

        panel.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        20,
                        0
                )
        );

        // Card 1
        JPanel totalCard =
                createStatCard(
                        "TOTAL STUDENTS",
                        "0",
                        "👥"
                );

        totalStudentsLabel =
                getValueLabel(totalCard);

        // Card 2
        JPanel averageCard =
                createStatCard(
                        "AVERAGE MARK",
                        "0",
                        "📊"
                );

        averageLabel =
                getValueLabel(averageCard);

        // Card 3
        JPanel gradeCard =
                createStatCard(
                        "TOP GRADE",
                        "-",
                        "🏆"
                );

        topGradeLabel =
                getValueLabel(gradeCard);

        panel.add(totalCard);
        panel.add(averageCard);
        panel.add(gradeCard);

        return panel;
    }

    JPanel createStatCard(
            String title,
            String value,
            String icon
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(cardColor);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        230,
                                        233,
                                        238
                                )
                        ),
                        new EmptyBorder(
                                18,
                                18,
                                18,
                                18
                        )
                )
        );

        JLabel iconLabel =
                new JLabel(icon);

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        28
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        titleLabel.setForeground(
                secondaryColor
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25
                )
        );

        valueLabel.setForeground(
                textColor
        );

        JPanel details =
                new JPanel();

        details.setBackground(
                cardColor
        );

        details.setLayout(
                new BoxLayout(
                        details,
                        BoxLayout.Y_AXIS
                )
        );

        details.add(titleLabel);

        details.add(
                Box.createVerticalStrut(5)
        );

        details.add(valueLabel);

        card.add(
                iconLabel,
                BorderLayout.WEST
        );

        card.add(
                details,
                BorderLayout.CENTER
        );

        return card;
    }

    JLabel getValueLabel(JPanel card) {

        JPanel details =
                (JPanel) card.getComponent(1);

        return (JLabel) details.getComponent(2);
    }

    // ============================================================
    // TABLE
    // ============================================================

    JPanel createTablePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(cardColor);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        230,
                                        233,
                                        238
                                )
                        ),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setBackground(cardColor);

        JLabel title =
                new JLabel(
                        "Student Records"
                );

        title.setFont(headingFont);
        title.setForeground(textColor);

        searchField =
                new JTextField();

        searchField.setPreferredSize(
                new Dimension(
                        220,
                        35
                )
        );

        searchField.setToolTipText(
                "Search by roll number or name"
        );

        searchField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        223,
                                        228
                                )
                        ),
                        new EmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        searchField.addActionListener(
                e -> searchFromTable()
        );

        top.add(
                title,
                BorderLayout.WEST
        );

        top.add(
                searchField,
                BorderLayout.EAST
        );

        panel.add(
                top,
                BorderLayout.NORTH
        );

        // Table columns

        String[] columns = {
                "Roll No",
                "Name",
                "Phone",
                "Mark 1",
                "Mark 2",
                "Mark 3",
                "Total",
                "Average",
                "Grade"
        };

        model =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        table =
                new JTable(model);

        table.setRowHeight(40);

        table.setFont(normalFont);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.setGridColor(
                new Color(
                        235,
                        237,
                        240
                )
        );

        table.setShowVerticalLines(false);

        table.getTableHeader()
                .setFont(boldFont);

        table.getTableHeader()
                .setBackground(
                        new Color(
                                245,
                                247,
                                250
                        )
                );

        table.getTableHeader()
                .setForeground(textColor);

        JScrollPane scroll =
                new JScrollPane(table);

        scroll.setBorder(null);

        panel.add(
                scroll,
                BorderLayout.CENTER
        );

        // Bottom buttons

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        bottom.setBackground(cardColor);

        JButton view =
                new JButton("View Details");

        JButton delete =
                new JButton("Delete");

        view.setFocusPainted(false);
        delete.setFocusPainted(false);

        view.addActionListener(
                e -> viewSelectedStudent()
        );

        delete.addActionListener(
                e -> deleteSelectedStudent()
        );

        bottom.add(view);
        bottom.add(delete);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        return panel;
    }

    // ============================================================
    // ADD STUDENT
    // ============================================================

    void showAddStudentDialog() {

        JDialog dialog =
                new JDialog(
                        this,
                        "Add New Student",
                        true
                );

        dialog.setSize(
                500,
                600
        );

        dialog.setLocationRelativeTo(this);

        JPanel panel =
                new JPanel();

        panel.setBackground(cardColor);

        panel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "Add New Student"
                );

        title.setFont(titleFont);
        title.setForeground(textColor);

        panel.add(title);

        panel.add(
                Box.createVerticalStrut(20)
        );

        nameField =
                createInputField(
                        panel,
                        "Student Name"
                );

        addressField =
                createInputField(
                        panel,
                        "Address"
                );

        phoneField =
                createInputField(
                        panel,
                        "Phone Number"
                );

        mark1Field =
                createInputField(
                        panel,
                        "Mark 1"
                );

        mark2Field =
                createInputField(
                        panel,
                        "Mark 2"
                );

        mark3Field =
                createInputField(
                        panel,
                        "Mark 3"
                );

        panel.add(
                Box.createVerticalStrut(15)
        );

        JButton addButton =
                new JButton(
                        "ADD STUDENT"
                );

        addButton.setBackground(
                accentColor
        );

        addButton.setForeground(
                Color.WHITE
        );

        addButton.setFont(boldFont);

        addButton.setFocusPainted(false);

        addButton.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        addButton.addActionListener(
                e -> {

                    if (addStudent()) {
                        dialog.dispose();
                    }
                }
        );

        panel.add(addButton);

        dialog.add(panel);

        dialog.setVisible(true);
    }

    JTextField createInputField(
            JPanel panel,
            String label
    ) {

        JLabel labelText =
                new JLabel(label);

        labelText.setFont(boldFont);
        labelText.setForeground(textColor);

        panel.add(labelText);

        panel.add(
                Box.createVerticalStrut(5)
        );

        JTextField field =
                new JTextField();

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        38
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        215,
                                        219,
                                        225
                                )
                        ),
                        new EmptyBorder(
                                8,
                                10,
                                8,
                                10
                        )
                )
        );

        panel.add(field);

        panel.add(
                Box.createVerticalStrut(12)
        );

        return field;
    }

    // ============================================================
    // ADD STUDENT LOGIC
    // ============================================================

    boolean addStudent() {

        try {

            String name =
                    nameField.getText().trim();

            String address =
                    addressField.getText().trim();

            String phone =
                    phoneField.getText().trim();

            int m1 =
                    Integer.parseInt(
                            mark1Field.getText().trim()
                    );

            int m2 =
                    Integer.parseInt(
                            mark2Field.getText().trim()
                    );

            int m3 =
                    Integer.parseInt(
                            mark3Field.getText().trim()
                    );

            if (name.isEmpty() ||
                    address.isEmpty() ||
                    phone.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields."
                );

                return false;
            }

            if (m1 < 0 || m1 > 100 ||
                    m2 < 0 || m2 > 100 ||
                    m3 < 0 || m3 > 100) {

                JOptionPane.showMessageDialog(
                        this,
                        "Marks must be between 0 and 100."
                );

                return false;
            }

            StudentRecords1 student =
                    new StudentRecords1();

            student.name = name;
            student.add = address;
            student.ph = phone;

            student.m1 = m1;
            student.m2 = m2;
            student.m3 = m3;

            student.tot =
                    m1 + m2 + m3;

            student.avg =
                    student.tot / 3.0;

            if (student.avg >= 90)
                student.grade = 'O';

            else if (student.avg >= 80)
                student.grade = 'A';

            else if (student.avg >= 70)
                student.grade = 'B';

            else if (student.avg >= 60)
                student.grade = 'C';

            else
                student.grade = 'F';

            int rollNo = ++startingPt;

            map.put(
                    rollNo,
                    student
            );

            refreshTable();

            updateStatistics();

            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully!\nRoll No: "
                            + rollNo,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return true;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid marks."
            );

            return false;
        }
    }

    // ============================================================
    // REFRESH TABLE
    // ============================================================

    void refreshTable() {

        model.setRowCount(0);

        for (Map.Entry<Integer, StudentRecords1> entry :
                map.entrySet()) {

            int rollNo = entry.getKey();

            StudentRecords1 student =
                    entry.getValue();

            model.addRow(
                    new Object[]{
                            rollNo,
                            student.name,
                            student.ph,
                            student.m1,
                            student.m2,
                            student.m3,
                            student.tot,
                            String.format(
                                    "%.2f",
                                    student.avg
                            ),
                            student.grade
                    }
            );
        }
    }

    // ============================================================
    // STATISTICS
    // ============================================================

    void updateStatistics() {

        totalStudentsLabel.setText(
                String.valueOf(
                        map.size()
                )
        );

        if (map.isEmpty()) {

            averageLabel.setText("0");
            topGradeLabel.setText("-");

            return;
        }

        double totalAverage = 0;

        for (StudentRecords1 student :
                map.values()) {

            totalAverage += student.avg;
        }

        double average =
                totalAverage / map.size();

        averageLabel.setText(
                String.format(
                        "%.1f",
                        average
                )
        );

        char bestGrade = 'F';

        for (StudentRecords1 student :
                map.values()) {

            if (gradeValue(student.grade)
                    > gradeValue(bestGrade)) {

                bestGrade =
                        student.grade;
            }
        }

        topGradeLabel.setText(
                String.valueOf(bestGrade)
        );
    }

    int gradeValue(char grade) {

        switch (grade) {

            case 'O':
                return 5;

            case 'A':
                return 4;

            case 'B':
                return 3;

            case 'C':
                return 2;

            default:
                return 1;
        }
    }

    // ============================================================
    // SEARCH
    // ============================================================

    void searchStudent() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Roll Number or Name:"
                );

        if (input == null ||
                input.trim().isEmpty()) {

            return;
        }

        input =
                input.trim();

        model.setRowCount(0);

        boolean found = false;

        for (Map.Entry<Integer, StudentRecords1> entry :
                map.entrySet()) {

            int roll =
                    entry.getKey();

            StudentRecords1 student =
                    entry.getValue();

            if (String.valueOf(roll)
                    .equals(input)
                    ||
                    student.name
                            .equalsIgnoreCase(input)) {

                addStudentToTable(
                        roll,
                        student
                );

                found = true;
            }
        }

        if (!found) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student not found."
            );

            refreshTable();
        }
    }

    void searchFromTable() {

        String input =
                searchField
                        .getText()
                        .trim();

        if (input.isEmpty()) {

            refreshTable();

            return;
        }

        model.setRowCount(0);

        for (Map.Entry<Integer, StudentRecords1> entry :
                map.entrySet()) {

            int roll =
                    entry.getKey();

            StudentRecords1 student =
                    entry.getValue();

            if (String.valueOf(roll)
                    .contains(input)
                    ||
                    student.name
                            .toLowerCase()
                            .contains(
                                    input.toLowerCase()
                            )) {

                addStudentToTable(
                        roll,
                        student
                );
            }
        }
    }

    void addStudentToTable(
            int roll,
            StudentRecords1 student
    ) {

        model.addRow(
                new Object[]{
                        roll,
                        student.name,
                        student.ph,
                        student.m1,
                        student.m2,
                        student.m3,
                        student.tot,
                        String.format(
                                "%.2f",
                                student.avg
                        ),
                        student.grade
                }
        );
    }

    // ============================================================
    // VIEW STUDENT
    // ============================================================

    void viewSelectedStudent() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student."
            );

            return;
        }

        int roll =
                Integer.parseInt(
                        table.getValueAt(
                                row,
                                0
                        ).toString()
                );

        StudentRecords1 student =
                map.get(roll);

        String details =
                "STUDENT DETAILS\n\n"
                        + "Roll No : " + roll + "\n"
                        + "Name    : " + student.name + "\n"
                        + "Address : " + student.add + "\n"
                        + "Phone   : " + student.ph + "\n\n"
                        + "Mark 1  : " + student.m1 + "\n"
                        + "Mark 2  : " + student.m2 + "\n"
                        + "Mark 3  : " + student.m3 + "\n"
                        + "Total   : " + student.tot + "\n"
                        + "Average : "
                        + String.format(
                                "%.2f",
                                student.avg
                        )
                        + "\n"
                        + "Grade   : " + student.grade;

        JOptionPane.showMessageDialog(
                this,
                details,
                "Student Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ============================================================
    // DELETE
    // ============================================================

    void deleteSelectedStudent() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student."
            );

            return;
        }

        int roll =
                Integer.parseInt(
                        table.getValueAt(
                                row,
                                0
                        ).toString()
                );

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete student with Roll No "
                                + roll + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            map.remove(roll);

            refreshTable();

            updateStatistics();
        }
    }

    void deleteStudent() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Roll Number:"
                );

        if (input == null)
            return;

        try {

            int roll =
                    Integer.parseInt(
                            input.trim()
                    );

            if (!map.containsKey(roll)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student not found."
                );

                return;
            }

            map.remove(roll);

            refreshTable();

            updateStatistics();

            JOptionPane.showMessageDialog(
                    this,
                    "Student deleted successfully."
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid roll number."
            );
        }
    }

    // ============================================================
    // MAIN
    // ============================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StudentRecordsGUI gui =
                    new StudentRecordsGUI();

            gui.setVisible(true);
        });
    }
}