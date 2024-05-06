import pandas as pd
import json
from utils import load_data, clean_numeric_columns, clean_text_columns, normalize_text, jsonify_data


def calculate_monthly_sales(file, date_col, sales_col):
    """Calculate monthly sales trends."""
    file[date_col] = pd.to_datetime(file[date_col])  # Ensure SaleDate is datetime
    file['Month'] = file[date_col].dt.to_period('M')  # Extract month and year
    file['Month'] = file['Month'].astype(str) # to string
    monthly_sales = file.groupby('Month')[sales_col].sum()  # Sum sales by month
    return monthly_sales

def calculate_best_selling_quantity(file, item_column, quantity_col):
    """Calculate best selling product by quantity"""
    best_selling_quantity = file.groupby(item_column)[quantity_col].sum().sort_values(ascending=False)

    return best_selling_quantity

def calculate_best_selling_amount(file, item_column, amount_col):
    """Calculate best selling product by quantity"""
    best_selling_amount = file.groupby(item_column)[amount_col].sum().sort_values(ascending=False)

    return best_selling_amount

''' 1. Did not count Staff items as sell, neither in sales amount or quantity
    2. Same item description will have different itemID, count by item description text
    3. Dirty data - like Winter WE GreenFee and Winter WE Green Fee - actually the same (note categorize)
'''
def main():
    pd.set_option('display.max_rows', None)
    data_path = '../../resources/data/Louisville_Metro_KY_-_Parks_Golf_Sales_Detail.csv'

    # Load the data
    file = load_data(data_path)

    # Clean the data
    columns_to_clean = ['ActualPrice', 'Quantity', 'SalesTax']
    text_columns_to_clean = ['ItemDescription']
    clean_numeric_columns(file, columns_to_clean)
    clean_text_columns(file, text_columns_to_clean)

    # Calculate total sales amount
    file['TotalSaleAmount'] = file['ActualPrice'] * file['Quantity']
    total_sales_amount = file['TotalSaleAmount'].sum()
    total_sales_quantity = file['Quantity'].sum()

    # Calculate the monthly sales amount
    monthly_sales = calculate_monthly_sales(file, 'SaleDate', 'TotalSaleAmount')

    # Calculate best sellers by quantity
    item_column = ['ItemDescription']
    best_selling_quantity = calculate_best_selling_quantity(file, item_column, "Quantity")

    # Calculate best sellers by amount
    best_selling_amount = calculate_best_selling_amount(file, item_column, "TotalSaleAmount")

    sales_data = {
        "totalSalesAmount": total_sales_amount,
        "totalSalesQuantity": total_sales_quantity,
        "monthlySales": jsonify_data(monthly_sales),
        "bestSellingByQuantity": jsonify_data(best_selling_quantity),
        "bestSellingByAmount": jsonify_data(best_selling_amount)
    }

    # Write to JSON file
    with open('../../resources/data/sales_data.json', 'w') as f:
        json.dump(sales_data, f, indent=4)

    print("Sales data written to sales_data.json")

    with open('../../resources/data/sales_report.txt', 'w') as f:
        f.write("Total Sales Amount: " + str(total_sales_amount) + '\n')
        f.write("----------------------------------------------------------------" + '\n')
        f.write("Total Sales Quantity: " + str(total_sales_quantity) + '\n')
        f.write("----------------------------------------------------------------" + '\n')
        f.write("Monthly Sales Trends:\n\n" + str(monthly_sales) + '\n')
        f.write("----------------------------------------------------------------" + '\n')
        f.write("Best Selling Items by quantity:\n\n" + str(best_selling_quantity) + '\n')
        f.write("----------------------------------------------------------------" + '\n')
        f.write("Best Selling Items by amount:\n\n" + str(best_selling_amount) + '\n')

if __name__ == "__main__":
    main()