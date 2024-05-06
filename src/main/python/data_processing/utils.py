import pandas as pd
import re
import json

def load_data(filepath):
    """Load the data from a CSV file."""
    return pd.read_csv(filepath, low_memory=False)

def clean_numeric_columns(df, column_names):
    """Convert specified columns to numeric, handling errors in-place."""
    for column in column_names:
        df[column] = pd.to_numeric(df[column], errors='coerce')

def clean_text_columns(df, column_names):
    """Strip leading and trailing spaces from text columns."""
    for column in column_names:
        df[column] = df[column].astype(str).str.strip().apply(normalize_text)

def normalize_text(text):
    """Easily add/delete rules"""
    # Convert to lowercase
    text = text.lower()
    # Standardize common abbreviations and variations
    text = text.replace('hotdog', 'hot dog')
    text = text.replace('sandwiches', 'sandwich')
    text = text.replace('cheeseburger', 'cheese burger')
    text = text.replace('chips', 'chip')
    text = text.replace('beverages', 'beverage')
    text = text.replace('drinks', 'drink')
    text = text.replace('bananna', 'banana')
    text = text.replace('drink - large', 'large drink')
    text = text.replace('drink - small', 'small drink')
    # Replace common product specifications
    text = re.sub(r'\b(\d+) pack\b', r'\1pack', text)  # Remove spaces in '6 pack', '12 pack', etc.
    text = re.sub(r'\b(\d+)pc\b', r'\1 pc', text)  # Standardize '6pc' to '6 pc'
    text = text.replace('twelve', '12').replace('six', '6')  # Standardize numeric words to numbers
    return text

def jsonify_data(data):
    return data.reset_index().to_dict(orient='records')