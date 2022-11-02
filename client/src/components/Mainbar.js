import styled from 'styled-components';

const Mainbar = styled.div`
  width: calc(100% - 300px - 24px);
  float: left;
  margin: 0;
  padding: 0;

  @media screen and (max-width: 980px) {
    width: 100%;
    float: none;
  }
`;

export default Mainbar;
