import styled from 'styled-components';

const SearchbarContainer = styled.form`
  padding: 0 8px;
  position: relative;
`;

const SearchbarInput = styled.input`
  width: ${(props) => props.width || '756px'};
  height: ${(props) => props.height || '30px'};

  border: 1px solid #babfc4;
  border-radius: 3px;
  padding-left: 30px;

  &:focus {
    outline: none;
    border: 1px solid #6bbbf7;
    box-shadow: 0px 0px 0px 4px #d8e5f2;
  }
`;

const SearchIcon = styled.div`
  i {
    position: absolute;
    left: 15px;
    top: 8px;
    color: black;
    font-size: 20px;
    color: #81878c;
  }
`;

const Searchbar = ({ width, height, placeholder }) => {
  return (
    <SearchbarContainer>
      <SearchbarInput
        width={width}
        height={height}
        type="text"
        placeholder={placeholder}
      ></SearchbarInput>
      <SearchIcon>
        <i className="fa-sharp fa-solid fa-magnifying-glass search_icon"></i>
      </SearchIcon>
    </SearchbarContainer>
  );
};

export default Searchbar;
