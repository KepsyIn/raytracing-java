JAVAC = javac
SRC_DIR = .
TARGET_DIR = bin
SOURCES = $(wildcard $(SRC_DIR)/*.java) $(wildcard $(SRC_DIR)/**/*.java)
CLASSES = $(patsubst $(SRC_DIR)/%.java,$(TARGET_DIR)/%.class,$(SOURCES))

# Default target
all: $(TARGET_DIR)

# Create target directory and compile
$(TARGET_DIR): $(SOURCES)
	@mkdir -p $(TARGET_DIR)
	$(JAVAC) -d $(TARGET_DIR) $(SOURCES)

# Clean target
clean:
	rm -rf $(TARGET_DIR)