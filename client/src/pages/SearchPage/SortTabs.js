import { useDispatch, useSelector } from 'react-redux';
import styled from 'styled-components';
import { getSearchList } from '../../_actions/search_action';

const FlexWrapper = styled.div`
  display: flex !important;
  margin-bottom: 16px !important;
  align-items: center !important;
`;

const Fl1 = styled.div`
  font-size: 1.30769231rem !important;
  margin-right: 12px !important;
  flex: 1 auto !important;
`;

const Tabs = styled.div`
  display: flex !important;
  flex-wrap: wrap;
  margin-bottom: 1px;
`;

const Tab = styled.button`
  position: relative;
  display: inline-block;
  padding: 0.8em;
  border: 1px solid transparent;
  border-radius: 3px;
  background-color: transparent;
  outline: none;
  font-family: inherit;
  font-size: 13px;
  font-weight: normal;
  line-height: calc((13 + 2) / 13);
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  user-select: none;
  border-color: #9fa6ad;
  margin-bottom: -1px;
  white-space: nowrap;
  color: #6a737c;

  :first-child:not(:only-child) {
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
  }
  :not(:first-child):not(:last-child) {
    border-radius: 0;
  }
  :not(:last-child) {
    margin-right: -1px;
  }
  :last-child:not(:only-child) {
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
  }
  :hover,
  :focus,
  :active {
    text-decoration: none;
    color: #525960;
    background-color: #f8f9f9;
  }
  :active {
    z-index: 30;
    background: #f1f2f3;
  }
  :focus {
    box-shadow: 0 0 0 4px #1a232629;
    outline: none;
  }
  &.is-selected {
    border-color: #838c95;
    z-index: 25;
    color: #3b4045;
    background-color: #e3e6e8;
    box-shadow: none;
  }
  &.is-selected:active {
    box-shadow: 0 0 0 4px #1a232629;
    outline: none;
  }
`;

export const SortTabObj = {
  Newest: 'newest',
  Score: 'score',
};

const SortTabs = () => {
  const dispatch = useDispatch();
  const search = useSelector((state) => state.search);
  const { sortTabValue } = search;

  const handleClickTab = (e) => {
    const params = {
      ...search,
      sortTabValue: e.target.textContent.toLowerCase(),
      pagerValue: 1,
    };
    dispatch(getSearchList(params));
  };

  const { Newest, Score } = SortTabObj;

  return (
    <FlexWrapper>
      <Fl1>120,823 results</Fl1>
      <div>
        <Tabs>
          <Tab
            className={sortTabValue === Newest ? 'is-selected' : ''}
            title="Questions with the most views, most answers, and highest score over the last few days"
            onClick={handleClickTab}
          >
            Newest
          </Tab>
          <Tab
            className={sortTabValue === Score ? 'is-selected' : ''}
            title="Questions with the most views, most answers, and highest score this week"
            onClick={handleClickTab}
          >
            Score
          </Tab>
        </Tabs>
      </div>
    </FlexWrapper>
  );
};

export default SortTabs;
