import styled from 'styled-components';

const HeaderWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  margin-top: 10px;
  h1 {
    margin-left: 10px;
    font-size: 22px;
    font-weight: bold;
    @media screen and (min-width: 641px) {
      font-size: 27px;
    }
  }
  img {
    display: none;
    width: 600px;
    @media screen and (min-width: 1050px) {
      display: block;
    }
  }
  @media screen and (min-width: 1101px) {
    max-width: 1150px;
  }
`;

const AskPageHeader = () => {
  return (
    <HeaderWrapper>
      <h1>Ask a public question</h1>
      <img
        src="https://cdn.sstatic.net/Img/ask/background.svg?v=c56910988bdf"
        alt="question mark robot"
      />
    </HeaderWrapper>
  );
};

export default AskPageHeader;
