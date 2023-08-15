import pandas as pd
import pymysql
from sqlalchemy import create_engine


def big_counter(word: pd.DataFrame) -> pd.DataFrame:
    df = word[(word['area'] >= 3000000) | (word['population'] >= 25000000)]
    return df[['name','population','area']]


if __name__ == '__main__':
    engine = create_engine("mysql+pymysql://username:1980Ak47_@localhost:3306/panda")
    df = pd.read_sql_query('select * from world', engine)
    result = big_counter(df)
    print(result.head())
