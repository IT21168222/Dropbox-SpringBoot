import React, {Component} from 'react';
import PropTypes from 'prop-types';
import swal from 'sweetalert';
import axios from 'axios';
import Grouplist from "./Grouplist";
class Groups extends Component {
    static propTypes = {
        message: PropTypes.string.isRequired
    };

    constructor(props) {
        super(props);
        this.state = {
            groups: [],
        };
    }

    componentDidMount() {
        axios.create({withCredentials: true}).get('http://localhost:8080/groups/listgroups',
            {}).then((response) => {
            this.setState({
                groups: response.data
            });
            console.log(response.data);
        }).catch((error) => {
            console.log("error in groups", error);
        });
    }

    componentWillMount() {
        axios.create({withCredentials: true}).get('http://localhost:8080/groups/listgroups',
            {}).then((response) => {
            this.setState({
                groups: response.data
            });
            console.log(response.data);
        }).catch((error) => {
            console.log("error in groups", error);
        });
    }

    creategroup=()=>{
        var groupname='';
        swal( "Enter GroupName:", {
            content:"input",
        })
            .then((value) => {
                groupname= `${value}`;
                axios.create({withCredentials:true}).post('http://localhost:8080/groups/Groupcreation',
                    {
                        group: groupname
                    }).then((response) => {

                    axios.create({withCredentials: true}).get('http://localhost:8080/groups/listgroups',
                        {}).then((response1) => {

                        this.setState({
                            groups: response1.data
                        });

                    }).catch((error) => {
                        console.log("error in groups", error);
                    });

                }).catch((error) => {
                    console.log("error in groups", error);
                });
                swal(`Group Created Successfully`);
            });
    };

    render() {
        return (
            <div>
                <br/>
                <img src="https://cfl.dropboxstatic.com/static/images/logo_catalog/dropbox_logo_glyph_2015_2016-vflzSDxC1.svg" height="40"/>
                <img src="https://cfl.dropboxstatic.com/static/images/logo_catalog/dropbox_logo_text_2015_2016-vflQnXBUU.svg" height="40" />
                <hr/>
                <p className={'basic_info'}><h2>Groups</h2></p>
                <div className="container-fluid"><div className={'rightfloat'}> <button className="btn btn-primary" onClick={() => this.creategroup()}>Create a group</button></div>
                    <Grouplist groups={this.state.groups}/>
                </div>
            </div>
        );
    }
}

export default Groups;